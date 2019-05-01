package edu.unq.desapp.eventeando

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("app")
class AppProperties {
    lateinit var version: String
}