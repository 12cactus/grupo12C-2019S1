package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.element.EventExpense
import edu.unq.desapp.eventeando.guest.User
import edu.unq.desapp.eventeando.guest.exception.CannotConfirmAssitanceException
import java.time.LocalDate

/**
 * Models an abstract event thats have spendings, guests and confirmationDate.
 * To review
 */
abstract class Event(val organizers: MutableList<User>,
                     val date: LocalDate,
                     val confirmationAllowedDate: LocalDate, //Por ahora todos van a tener esto.
                     val guests: MutableList<User> = mutableListOf(),
                     val eventExpenses: MutableList<EventExpense> = mutableListOf()) {
    /**
     * Returns a list with the guests who confirmed attendance
     */
    fun confirmedGuests(): List<User> {
        return guests.filter { guest -> guest.isConfirmedFor(this) }
    }

    /**
     * TODO
     */
    fun invite(user: User) {
        this.guests.add(user)
        user.invitationFrom(this)
    }

    /**
     * Each subclass responsability. Returns total cost of the event
     */
    abstract fun totalCost(): Double

    /**
     * TODO
     */
    fun confirmAssistance(guest: User) {
        val today = LocalDate.now()
        if (isConfirmationAllowedFor(today)) {
            guest.attendTo(this)
        } else {
            throw CannotConfirmAssitanceException("The confirmation date expired")
        }
    }


    /**
     * Encapsulates the logic about whether a date is allowed to be confirmed
     */
    private fun isConfirmationAllowedFor(today: LocalDate) =
            today.isBefore(confirmationAllowedDate) || today.isEqual(confirmationAllowedDate)

    /**
     * TODO
     */
    fun numberOfGuests(): Int {
        return guests.size
    }

    /**
     * TODO
     */
    fun numberOfConfirmedGuests(): Int {
        return confirmedGuests().size
    }

    /**
     * TODO
     */
    fun addCommodity(commodity: EventExpense) {
        eventExpenses.add(commodity)
    }
}

enum class EventType(var value:String){
    PARTY("Party"),
    BASKET("Basket"),
    POOLMONEY("PoolMoney")
    //buy first, money first
}
