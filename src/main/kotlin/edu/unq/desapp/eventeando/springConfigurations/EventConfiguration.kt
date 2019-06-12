package edu.unq.desapp.eventeando.springConfigurations

import edu.unq.desapp.eventeando.event.EventCreator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventConfiguration {

    @Bean
    fun eventCreator(): EventCreator {
        return EventCreator.create()
    }
}
