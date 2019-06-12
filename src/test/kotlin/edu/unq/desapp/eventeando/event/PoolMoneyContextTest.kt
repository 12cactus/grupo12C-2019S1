package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.element.EventExpense
import edu.unq.desapp.eventeando.element.EventExpenseConfiguration
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate
import java.util.function.Supplier

interface PoolMoneyContextTest: TestContext {
    fun poolMoney(): PoolMoney
    fun poolMoney(supplier: Supplier<PoolMoney>)

    fun limitDateToConfirm(): LocalDate
    fun limitDateToConfirm(supplier: Supplier<LocalDate>)

    fun poolMoneyDate(): LocalDate
    fun poolMoneyDate(supplier: Supplier<LocalDate>)

    fun organizer(): User
    fun organizer(supplier: Supplier<User>)

    fun organizers(): MutableList<User>
    fun organizers(supplier: Supplier<MutableList<User>>)

    fun expense(): EventExpense
    fun expense(supplier: Supplier<EventExpense>)

    fun otherExpense(): EventExpense
    fun otherExpense(supplier: Supplier<EventExpense>)

    fun guest(): User
    fun guest(supplier: Supplier<User>)

    fun otherGuest(): User
    fun otherGuest(supplier: Supplier<User>)

    fun otherEventExpenseConfiguration(): EventExpenseConfiguration
    fun otherEventExpenseConfiguration(supplier: Supplier<EventExpenseConfiguration>)

    fun eventExpenseConfiguration(): EventExpenseConfiguration
    fun eventExpenseConfiguration(supplier: Supplier<EventExpenseConfiguration>)

    fun eventExpenseCalculator(): EventExpenseCalculator
    fun eventExpenseCalculator(supplier: Supplier<EventExpenseCalculator?>)

    fun eventExpenseConfigurations(): MutableList<EventExpenseConfiguration>
    fun eventExpenseConfigurations(supplier: Supplier<MutableList<EventExpenseConfiguration>>)

    fun guests(): MutableList<User>
    fun guests(supplier: Supplier<MutableList<User>>)

    fun eventExpenses(): MutableList<EventExpense>
    fun eventExpenses(supplier: Supplier<MutableList<EventExpense>>)

}
