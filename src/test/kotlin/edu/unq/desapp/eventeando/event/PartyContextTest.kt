package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.element.EventExpenseConfiguration
import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate
import java.util.function.Supplier


interface PartyContextTest : TestContext {
    fun party(): Party
    fun party(supplier: Supplier<Party>)

    fun limitDateToConfirm(): LocalDate
    fun limitDateToConfirm(supplier: Supplier<LocalDate>)

    fun partyDate(): LocalDate
    fun partyDate(supplier: Supplier<LocalDate>)

    fun organizer(): User
    fun organizer(supplier: Supplier<User>)

    fun organizers(): MutableList<User>
    fun organizers(supplier: Supplier<MutableList<User>>)

    fun product(): Product
    fun product(supplier: Supplier<Product>)

    fun otherProduct(): Product
    fun otherProduct(supplier: Supplier<Product>)

    fun guest(): User
    fun guest(supplier: Supplier<User>)

    fun otherGuest(): User
    fun otherGuest(supplier: Supplier<User>)

    fun otherEventExpenseConfiguration(): EventExpenseConfiguration
    fun otherEventExpenseConfiguration(supplier: Supplier<EventExpenseConfiguration>)

    fun eventExpenseConfiguration(): EventExpenseConfiguration
    fun eventExpenseConfiguration(supplier: Supplier<EventExpenseConfiguration>)

    fun eventExpenseCalculator(): EventExpenseCalculator
    fun eventExpenseCalculator(supplier: Supplier<EventExpenseCalculator>)

    fun eventExpenseConfigurations(): MutableList<EventExpenseConfiguration>
    fun eventExpenseConfigurations(supplier: Supplier<MutableList<EventExpenseConfiguration>>)

    fun guests(): MutableList<User>
    fun guests(supplier: Supplier<MutableList<User>>)
}
