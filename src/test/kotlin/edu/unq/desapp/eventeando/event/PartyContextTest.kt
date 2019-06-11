package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.element.Commodity
import edu.unq.desapp.eventeando.element.Product
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

    fun product(): Product
    fun product(supplier: Supplier<Product>)

    fun otherProduct(): Product
    fun otherProduct(supplier: Supplier<Product>)

    fun guest(): User
    fun guest(supplier: Supplier<User>)

    fun otherGuest(): User
    fun otherGuest(supplier: Supplier<User>)

    fun commodity(): Commodity
    fun commodity(supplier: Supplier<Commodity>)

    fun otherCommodity(): Commodity
    fun otherCommodity(supplier: Supplier<Commodity>)
}
