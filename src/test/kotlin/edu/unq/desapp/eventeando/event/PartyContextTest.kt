package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate
import java.util.function.Supplier


interface PartyContextTest : TestContext{
    fun party(): Party
    fun party(supplier: Supplier<Party>)

    fun limitDateToConfirm(): LocalDate
    fun limitDateToConfirm(supplier: Supplier<LocalDate>)

    fun partyDate(): LocalDate
    fun partyDate(supplier: Supplier<LocalDate>)

    fun organizer(): String
    fun organizer(supplier: Supplier<String>)

    fun guests(): MutableList<Guest>
    fun guests(supplier: Supplier<MutableList<Guest>>)

    fun products(): MutableList<Product>
    fun products(supplier: Supplier<MutableList<Product>>)

    fun product(): Product
    fun product(supplier: Supplier<Product>)

    fun otherProduct(): Product
    fun otherProduct(supplier: Supplier<Product>)

    fun guest(): Guest
    fun guest(supplier: Supplier<Guest>)

    fun otherGuest(): Guest
    fun otherGuest(supplier: Supplier<Guest>)

    fun spendings(): MutableList<Spending>
    fun spendings(supplier: Supplier<MutableList<Spending>>)

    fun spending(): Spending
    fun spending(supplier: Supplier<Spending>)



}
