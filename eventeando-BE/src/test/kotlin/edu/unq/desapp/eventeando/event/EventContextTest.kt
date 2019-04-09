package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import java.util.function.Supplier

interface EventContextTest : TestContext{
    fun event(): Event
    fun event(s: Supplier<Event>)
}
