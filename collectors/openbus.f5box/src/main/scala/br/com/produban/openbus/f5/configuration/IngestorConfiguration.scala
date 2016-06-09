package br.com.produban.openbus.f5.configuration

import java.io.File

import br.com.openbus.publisher.kafka.KafkaAvroPublisher
import br.com.produban.openbus.ingestor.OpenbusDataIngestion
import org.apache.avro.Schema
import org.apache.avro.generic.GenericRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class IngestorConfiguration {

  @Value("${kafka.config.brokers.host}")
  var kafkaBrokerHost: String = _

  @Value("${kafka.config.required.acks}")
  var requireAcks: Boolean = _

  @Value("${kafka.config.async}")
  var isAsync: Boolean = _

  @Value("${kafka.config.batch.messages}")
  var batchMessages: Int = _

  @Value("${kafka.f5.success.topic}")
  var successTopic: String = _

  @Value("${kafka.f5.failure.topic}")
  var failureTopic: String = _

  @Value("${kafka.f5.avro.schema.f5box}")
  var schemaF5BoxPath: String = _

  @Value("${kafka.f5.avro.schema.virtual.server}")
  var schemaVirtualServerPath: String = _

  @Value("${kafka.f5.avro.schema.pool}")
  var schemaPoolPath: String = _

  @Value("${kafka.f5.avro.schema.f5box.certificate}")
  var schemaF5BoxCertificatePath: String = _

  @Bean
  def kafkaAvroPublishier(): KafkaAvroPublisher[GenericRecord] = {
    return new KafkaAvroPublisher[GenericRecord](kafkaBrokerHost, requireAcks, isAsync, batchMessages)
  }

  @Bean
  def ingestor(): OpenbusDataIngestion = {
    OpenbusDataIngestion.OpenbusDataIngestionBuilder
      .aIngestionConfiguration()
      .withKafkaAvroPublisher(kafkaAvroPublishier())
      .withSuccessTopic(successTopic)
      .withFailureTopic(failureTopic).build()
  }

  @Bean(name = Array("f5BoxSchema"))
  def f5BoxSchema(): Schema = {
    val parser = new Schema.Parser
    parser.parse(new File(schemaF5BoxPath))
  }

  @Bean(name = Array("virtualServerSchema"))
  def virtualServerSchema(): Schema = {
    val parser = new Schema.Parser
    parser.parse(new File(schemaVirtualServerPath))
  }

  @Bean(name = Array("poolSchema"))
  def poolSchema(): Schema = {
    val parser = new Schema.Parser
    parser.parse(new File(schemaPoolPath))
  }

  @Bean(name = Array("certificateSchema"))
  def certificateSchema(): Schema = {
    val parser = new Schema.Parser
    parser.parse(new File(schemaF5BoxCertificatePath))
  }
}
