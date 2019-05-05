package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.util.function.Supplier


interface PartyContextTest : TestContext{
    fun fiesta(): Party
    fun fiesta(supplier: Supplier<Party>)

    fun invitados(): MutableList<Guest>
    fun invitados(supplier: Supplier<MutableList<Guest>>)

    fun invitado(): Guest
    fun invitado(supplier: Supplier<Guest>)

    fun gastos(): MutableList<Spending>
    fun gastos(supplier: Supplier<MutableList<Spending>>)

    fun gasto(): Spending
    fun gasto(supplier: Supplier<Spending>)

}
