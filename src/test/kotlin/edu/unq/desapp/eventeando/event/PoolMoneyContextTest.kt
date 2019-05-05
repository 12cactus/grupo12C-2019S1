package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.util.function.Supplier

interface PoolMoneyContextTest: TestContext {

    fun poolMoney(): PoolMoney
    fun poolMoney(supplier: Supplier<PoolMoney>)

    fun guest(): Guest
    fun guest(supplier: Supplier<Guest>)

    fun otherGuest(): Guest
    fun otherGuest(supplier: Supplier<Guest>)

    fun spending(): Spending
    fun spending(supplier: Supplier<Spending>)

    fun otherSpend(): Spending
    fun otherSpend(supplier: Supplier<Spending>)

}
