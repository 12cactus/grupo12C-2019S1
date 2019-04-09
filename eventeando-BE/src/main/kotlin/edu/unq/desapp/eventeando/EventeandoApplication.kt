package edu.unq.desapp.eventeando

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Indica el punto de entrada para la aplicación de Spring.
 * Incluye un hook que loggea la versión al terminar la inicialización
 */
@SpringBootApplication
class EventeandoApplication

/**
 * Función que da el contexto de "ejecutable"
 * Se configura Spring para que falle en caso de overridear beans
 */
fun main(args: Array<String>) {
    runApplication<EventeandoApplication>(*args) {
        setAllowBeanDefinitionOverriding(false)
    }
}
