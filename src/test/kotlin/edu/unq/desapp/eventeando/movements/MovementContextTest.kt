package edu.unq.desapp.eventeando.movements

import edu.unq.desapp.eventeando.checkingAccount.Movement
import edu.unq.desapp.eventeando.event.Basket
import edu.unq.desapp.eventeando.guest.Guest
import ar.com.dgarcia.javaspec.api.contexts.TestContext
import java.util.function.Supplier

interface MovementContextTest: TestContext {

    fun deposito(): Movement
    fun deposito(supplier: Supplier<Movement>)

    fun retiro(): Movement
    fun retiro(supplier: Supplier<Movement>)

    fun invitado(): Guest
    fun invitado(supplier: Supplier<Guest>)

    fun canastaCon1Confirmado(): Basket
    fun canastaCon1Confirmado(supplier: Supplier<Basket>)

}
