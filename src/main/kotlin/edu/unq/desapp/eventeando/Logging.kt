package edu.unq.desapp.eventeando

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

/**
 * Define un extension method que nos da un Logger para esa clase
 */
fun <T : Any> T.getLogger(): Logger = LoggerFactory.getLogger(getClassForLogging(javaClass))

/**
 * Esta función determina el nombre real de la clase, quitando al companion object si es necesario
 */
fun <T : Any> getClassForLogging(javaClass: Class<T>): Class<*> {
    return javaClass.enclosingClass?.takeIf {
        it.kotlin.companionObject?.java == javaClass
    } ?: javaClass
}