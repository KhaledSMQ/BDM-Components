package br.com.produban.openbus.encoders

import br.com.produban.openbus.model.Event
import kafka.serializer.Encoder
import kafka.utils.VerifiableProperties

import br.com.produban.openbus.avro.AvroEncoder

import Event._

class KafkaAvroEncoder(val conf : VerifiableProperties = null) extends Encoder[String] {

  override def toBytes(json: String): Array[Byte] = {
    val payload = fromJson(json)
    AvroEncoder.toByteArray(payload.getOrElse("tool", "").asInstanceOf[String],new Event(payload).toSchemaInstance)
  }
}
