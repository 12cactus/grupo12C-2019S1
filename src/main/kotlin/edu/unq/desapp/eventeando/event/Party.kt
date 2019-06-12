package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.element.EventExpense
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate

/**
 * In this type of events the expenses of the same are not distributed but
 * they are paid by the organizer. When creating the event, the organizer
 * does not just enter a list of guests to invite but also how long before
 * they are allowed confirmations
 */
class Party(organizers: MutableList<User>,
            date: LocalDate,
            confirmationAllowedDate: LocalDate,
            private val eventExpenseCalculator: EventExpenseCalculator,
            guests: MutableList<User> = mutableListOf(),
            eventExpenses: MutableList<EventExpense> = mutableListOf()) :
        Event(organizers, date, confirmationAllowedDate, guests, eventExpenses) {

    /**
     * TODO
     */
    override fun totalCost(): Double {
        val numberOfConfirmedGuests = numberOfConfirmedGuests()
        return totalEventExpense() + eventExpenseCalculator.calculateTotalFor(numberOfConfirmedGuests)
    }

    private fun totalEventExpense(): Double {
        return eventExpenses
                .fold(0.00) { total, eventExpense -> total + eventExpense.price() }
    }
}
