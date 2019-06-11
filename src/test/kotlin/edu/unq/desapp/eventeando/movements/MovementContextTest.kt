package edu.unq.desapp.eventeando.movements

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.guest.User
import java.util.function.Supplier

interface MovementContextTest: TestContext {
    fun guest(): User
    fun guest(supplier: Supplier<User>)
}
