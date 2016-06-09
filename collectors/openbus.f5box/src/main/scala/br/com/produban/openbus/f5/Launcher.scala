package br.com.produban.openbus.f5

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableCaching
class LauncherConfiguration

object Launcher extends App {
  SpringApplication.run(classOf[LauncherConfiguration])
}
