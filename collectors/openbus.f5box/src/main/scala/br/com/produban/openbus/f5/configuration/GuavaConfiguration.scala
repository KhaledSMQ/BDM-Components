package br.com.produban.openbus.f5.configuration

import java.util.concurrent.TimeUnit

import com.google.common.cache.CacheBuilder
import org.springframework.cache.CacheManager
import org.springframework.cache.guava.GuavaCacheManager
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class GuavaConfiguration {

  @Bean
  def cacheManager() : CacheManager = {
    val cacheManager = new GuavaCacheManager
    cacheManager.setCacheBuilder(
      CacheBuilder.newBuilder().expireAfterWrite(3600, TimeUnit.SECONDS).maximumSize(100)
    )
    cacheManager
  }
}
