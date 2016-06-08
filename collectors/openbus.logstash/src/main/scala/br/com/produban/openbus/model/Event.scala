package br.com.produban.openbus.model

import java.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.apache.avro.generic.GenericRecord
import org.apache.commons.beanutils.{PropertyUtils, BeanUtils, ConstructorUtils}

import scala.collection.JavaConverters._

class Event(val eventMap : Map[String,Any]) {

  val reservedNames = List("schema_class","tool","@timestamp","@version")

  def toSchemaInstance : GenericRecord = {
    val clazz : Class[_] = Class.forName(eventMap("schema_class").asInstanceOf[String])
    if (classOf[GenericRecord].isAssignableFrom(clazz)) {
      val schemaInstance : Any = ConstructorUtils.invokeConstructor(clazz,null)

      def initWeakMap(bean:Any): Unit = {
        if (Option(PropertyUtils.getProperty(bean,"weakInfo")).isEmpty) {
          PropertyUtils.setProperty(bean,"weakInfo",new util.HashMap[String,String]())
        }
      }

      def ingestWeakAttribute(bean:Any,attributeName:String,attributeValue:Any): Unit = {
        if (PropertyUtils.isWriteable(bean,"weakInfo")) {
          initWeakMap(bean)
          PropertyUtils.setMappedProperty(bean, "weakInfo", attributeName, attributeValue)
        }
      }

      eventMap.foreach { pair:(String,Any) =>
        PropertyUtils.isWriteable(schemaInstance,pair._1) match {
          case true => PropertyUtils.setProperty(schemaInstance,pair._1,pair._2)
          case false if !reservedNames.contains(pair._1) => {
            ingestWeakAttribute(schemaInstance,pair._1,pair._2)
          }
          case _ =>
        }
      }

      schemaInstance.asInstanceOf[GenericRecord]
    } else
      throw new IllegalArgumentException("'schema_class' isn't a valid Avro schema class")
  }

}

object Event {

  val mapType = new TypeToken[java.util.Map[String,Any]](){}.getType

  def fromJson(json:String) : Map[String, Any] = {
    val map = new Gson().fromJson[java.util.Map[String, Any]](json, mapType).asScala.toMap
    map.find((pair) => pair._1.equals("schema_class") && pair._2.asInstanceOf[String].nonEmpty) match {
      case Some(_) => map
      case _ => throw new IllegalArgumentException("'schema_class' field is required")
    }
  }
}