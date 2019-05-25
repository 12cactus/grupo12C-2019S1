package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.User
import java.util.function.Supplier

interface PoolMoneyContextTest: TestContext {

    fun poolMoney(): PoolMoney
    fun poolMoney(supplier: Supplier<PoolMoney>)

    fun guest(): User
    fun guest(supplier: Supplier<User>)

    fun otherGuest(): User
    fun otherGuest(supplier: Supplier<User>)

    fun spending(): Spending
    fun spending(supplier: Supplier<Spending>)

    fun otherSpend(): Spending
    fun otherSpend(supplier: Supplier<Spending>)

}
