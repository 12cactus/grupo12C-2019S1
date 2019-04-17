package edu.unq.desapp.eventeando

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

/**
 * Loggea la versión de la aplicación al iniciar
 */
@Component
class OnStartupVersionLogger : ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    lateinit var appProperties: AppProperties

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        logger.info("Version ${appProperties.version}")
    }

    companion object {
        private val logger = getLogger()
    }

}