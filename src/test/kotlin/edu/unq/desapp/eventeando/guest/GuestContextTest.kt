package edu.unq.desapp.eventeando.guest

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.event.Event
import java.util.function.Supplier

interface GuestContextTest : TestContext{
    fun invitado(): Guest
    fun invitado(supplier: Supplier<Guest>)

    fun evento(): Event
    fun evento(supplier: Supplier<Event>)
}
