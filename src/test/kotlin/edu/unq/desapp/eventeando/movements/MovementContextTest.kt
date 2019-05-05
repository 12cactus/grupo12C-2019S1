package edu.unq.desapp.eventeando.movements

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.guest.Guest
import java.util.function.Supplier

interface MovementContextTest: TestContext {
    fun guest(): Guest
    fun guest(supplier: Supplier<Guest>)
}
