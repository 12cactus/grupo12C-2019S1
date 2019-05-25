package edu.unq.desapp.eventeando.guest

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.event.Event
import java.util.function.Supplier

interface GuestContextTest : TestContext{
    fun guest(): User
    fun guest(supplier: Supplier<User>)

    fun event(): Event
    fun event(supplier: Supplier<Event>)
}
