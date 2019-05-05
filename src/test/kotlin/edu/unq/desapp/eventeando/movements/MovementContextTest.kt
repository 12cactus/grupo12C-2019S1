package edu.unq.desapp.eventeando.movements

import edu.unq.desapp.eventeando.checkingAccount.Movement
import edu.unq.desapp.eventeando.event.Basket
import edu.unq.desapp.eventeando.guest.Guest
import ar.com.dgarcia.javaspec.api.contexts.TestContext
import java.util.function.Supplier

interface MovementContextTest: TestContext {

    fun bankDeposit(): Movement
    fun bankDeposit(supplier: Supplier<Movement>)

    fun bankWithdrawal(): Movement
    fun bankWithdrawal(supplier: Supplier<Movement>)

    fun guest(): Guest
    fun guest(supplier: Supplier<Guest>)

    fun basketWithAConfirmedGuest(): Basket
    fun basketWithAConfirmedGuest(supplier: Supplier<Basket>)

}
