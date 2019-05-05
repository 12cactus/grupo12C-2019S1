package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.util.function.Supplier

interface PoolMoneyContextTest: TestContext {

    fun baquita(): PoolMoney
    fun baquita(supplier: Supplier<PoolMoney>)

    fun invitado(): Guest
    fun invitado(supplier: Supplier<Guest>)

    fun otroInvitado(): Guest
    fun otroInvitado(supplier: Supplier<Guest>)

    fun gasto(): Spending
    fun gasto(supplier: Supplier<Spending>)

    fun otroGasto(): Spending
    fun otroGasto(supplier: Supplier<Spending>)

}
