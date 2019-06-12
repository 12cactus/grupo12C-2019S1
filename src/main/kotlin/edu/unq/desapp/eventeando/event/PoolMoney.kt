package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.element.EventExpense
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate

/**
 * Models a pool of money, thats have.....
 */
class PoolMoney(organizers: MutableList<User>,
                date: LocalDate,
                confirmationAllowedDate: LocalDate,
                private val manualConfirmedGuests: Int,
        //Tener esto o no es lo que diferencia entre buy first o money first
                private val eventExpenseCalculator: EventExpenseCalculator?,
                guests: MutableList<User> = mutableListOf(),
                eventExpenses: MutableList<EventExpense> = mutableListOf()) :
        Event(organizers, date, confirmationAllowedDate, guests, eventExpenses) {

    /**
     *
     */
    override fun costPerPerson(): Double {
        if (manualConfirmedGuests == 0) {
            throw DivideByZeroException("Cannot calculate. ManualConfirmedGuests must be higher than zero")
        }
        return roundUp(totalCost().div(manualConfirmedGuests))
    }

    private fun roundUp(number: Double) = Math.ceil(number)

    /**
     * TODO
     */
    override fun totalCost(): Double {
        val numberOfConfirmedGuests = numberOfConfirmedGuests()
        val totalEventExpense = totalEventExpense()
        if (eventExpenseCalculator == null) {
            return totalEventExpense
        }
        return totalEventExpense + eventExpenseCalculator.calculateTotalFor(numberOfConfirmedGuests)
    }

    private fun totalEventExpense(): Double {
        return eventExpenses
                .fold(0.00) { total, eventExpense -> total + eventExpense.price() }
    }
}
