package br.com.produban.openbus.model

import br.com.produban.openbus.BaseSpec
import br.com.produban.openbus.model.Event._
import br.com.produban.openbus.model.avro.{Storage, ZabbixAgentData}
import scala.collection.JavaConverters._

class EventSpec extends BaseSpec {
  "An event's json document" should "be deserialized as a map" in new EventContext {
    Event.fromJson(zabbixJson) should equal(eventMap)
  }

  "An event map" should "have a schema_class entry" in new EventContext {
    the [IllegalArgumentException] thrownBy {
      Event.fromJson(invalidJson)
    } should have message "'schema_class' field is required"
  }

  "An event map's 'schema_class' entry" should "be assignable to 'org.apache.avro.generic.GenericRecord'" in new EventContext {
    val schemaClassNotFoundEvent : Event = new Event(eventMap.updated("schema_class", "an.unknown.Class"))

    an [ClassNotFoundException] should be thrownBy schemaClassNotFoundEvent.toSchemaInstance

    val invalidSchemaClassEvent : Event = new Event(eventMap.updated("schema_class", "java.lang.Object"))

    the [IllegalArgumentException] thrownBy {
      invalidSchemaClassEvent.toSchemaInstance
    } should have message "'schema_class' isn't a valid Avro schema class"
  }

  "A given event map" should "be translated to its schema_class mirror object" in new EventContext {
    new Event(fromJson(zabbixJson)).toSchemaInstance should equal(flatSchemaInstance)
    new Event(fromJson(storageJson)).toSchemaInstance should equal(mappedSchemaInstance)
  }
}

trait EventContext {
  val zabbixJson : String = "{\"value\":\"scala\"," +
    "\"@version\":\"1\"," +
    "\"version\":\"1\"," +
    "\"@timestamp\":\"2014-11-03T12:41:15.336Z\"," +
    "\"timestamp\":\"2014-11-03T12:41:15.336Z\"," +
    "\"clock\":\"123456789\"," +
    "\"host\":\"produban-VirtualBox\"," +
    "\"key\":\"EventIdentified.CROND\"," +
    "\"schema_class\":\"br.com.produban.openbus.model.avro.ZabbixAgentData\"}"

  val storageJson : String = "{\"@version\":\"1\"," +
    "\"@timestamp\":\"2014-11-03T12:41:15.336Z\"," +
    "\"pool\":\"pool\"," +
    "\"propertyName\":\"propertyName\"," +
    "\"propertyValue\":\"propertyValue\"," +
    "\"storage\":\"storage\"," +
    "\"timestamp\":\"timestamp\"," +
    "\"weakKey\":\"weakValue\"," +
    "\"schema_class\":\"br.com.produban.openbus.model.avro.Storage\"}"

  val invalidJson : String = "{\"value\":\"scala\"," +
    "\"@version\":\"1\"," +
    "\"version\":\"1\"," +
    "\"@timestamp\":\"2014-11-03T12:41:15.336Z\"," +
    "\"timestamp\":\"2014-11-03T12:41:15.336Z\"," +
    "\"clock\":\"123456789\"," +
    "\"host\":\"produban-VirtualBox\"," +
    "\"key\":\"EventIdentified.CROND\"}"

  val eventMap : Map[String,String] = Map("value" -> "scala",
    "@version" -> "1",
    "version" -> "1",
    "@timestamp" -> "2014-11-03T12:41:15.336Z",
    "timestamp" -> "2014-11-03T12:41:15.336Z",
    "clock" -> "123456789",
    "host" -> "produban-VirtualBox",
    "key" -> "EventIdentified.CROND",
    "schema_class" -> "br.com.produban.openbus.model.avro.ZabbixAgentData")

  val flatSchemaInstance : ZabbixAgentData = ZabbixAgentData.newBuilder()
    .setValue(eventMap("value"))
    .setTimestamp(eventMap("timestamp"))
    .setClock(eventMap("clock"))
    .setHost(eventMap("host"))
    .setKey(eventMap("key"))
    .setHostMetadata(null)
    .setLastlogsize(null)
    .setEventid(null)
    .setMtime(null)
    .setNs(null)
    .setSeverity(null)
    .setSource(null)
    .setState(null)
    .build()

  val mappedSchemaInstance : Storage = Storage.newBuilder()
    .setPool("pool")
    .setPropertyName("propertyName")
    .setPropertyValue("propertyValue")
    .setStorage("storage")
    .setTimestamp("timestamp")
    .setWeakInfo(Map("weakKey" -> "weakValue").asJava)
    .build()
}