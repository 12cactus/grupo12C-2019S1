package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.util.function.Supplier


interface PartyContextTest : TestContext{
    fun party(): Party
    fun party(supplier: Supplier<Party>)

    fun guests(): MutableList<Guest>
    fun guests(supplier: Supplier<MutableList<Guest>>)

    fun guest(): Guest
    fun guest(supplier: Supplier<Guest>)

    fun spendings(): MutableList<Spending>
    fun spendings(supplier: Supplier<MutableList<Spending>>)

    fun spending(): Spending
    fun spending(supplier: Supplier<Spending>)

}
