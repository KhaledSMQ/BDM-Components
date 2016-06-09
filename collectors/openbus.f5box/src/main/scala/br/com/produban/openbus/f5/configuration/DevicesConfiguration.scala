package br.com.produban.openbus.f5.configuration

import java.io.File

import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}

import scala.collection.JavaConverters._

@Configuration
class DevicesConfiguration {

  val LOGGER = LoggerFactory.getLogger(classOf[DevicesConfiguration])

  @Value("${f5.devices.config.path}")
  var devicesConfigPath: String = _

  @Bean
  def ipAddresses(): List[String] = {
    val configFromPath = ConfigFactory.parseFile(new File(devicesConfigPath))

    if (configFromPath.isEmpty) {
      LOGGER.info("External config not specified ... Loading config from classpath !")
      ConfigFactory.load("devices").getStringList("devices.ipAddresses").asScala.toList
    } else {
      LOGGER.info("Loading external configuration from=" + devicesConfigPath)
      configFromPath.getStringList("devices.ipAddresses").asScala.toList
    }
  }
}
