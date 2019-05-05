package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.util.function.Supplier

interface BasketContextTest : TestContext{
    fun basket(): Basket
    fun basket(supplier: Supplier<Basket>)

    fun spendings(): MutableList<Spending>
    fun spendings(supplier: Supplier<MutableList<Spending>>)

    fun guests(): MutableList<Guest>
    fun guests(supplier: Supplier<MutableList<Guest>>)

    fun guest(): Guest
    fun guest(supplier: Supplier<Guest>)
}
