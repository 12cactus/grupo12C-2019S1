package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate
import java.util.function.Supplier


interface PartyContextTest : TestContext{
    fun party(): Party
    fun party(supplier: Supplier<Party>)

    fun limitDateToConfirm(): LocalDate
    fun limitDateToConfirm(supplier: Supplier<LocalDate>)

    fun partyDate(): LocalDate
    fun partyDate(supplier: Supplier<LocalDate>)

    fun organizer(): User
    fun organizer(supplier: Supplier<User>)

    fun users(): MutableList<User>
    fun users(supplier: Supplier<MutableList<User>>)

    fun product(): Product
    fun product(supplier: Supplier<Product>)

    fun otherProduct(): Product
    fun otherProduct(supplier: Supplier<Product>)

    fun guest(): User
    fun guest(supplier: Supplier<User>)

    fun otherGuest(): User
    fun otherGuest(supplier: Supplier<User>)

    fun spendings(): MutableList<Spending>
    fun spendings(supplier: Supplier<MutableList<Spending>>)

    fun spending(): Spending
    fun spending(supplier: Supplier<Spending>)

    fun otherSpending(): Spending
    fun otherSpending(supplier: Supplier<Spending>)

}
