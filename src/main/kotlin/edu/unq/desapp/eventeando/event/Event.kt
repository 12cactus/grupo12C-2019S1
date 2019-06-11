package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.guest.User
import edu.unq.desapp.eventeando.guest.exception.CannotConfirmAssitanceException
import java.time.LocalDate

/**
 * Models an abstract event thats have spendings, guests and confirmationDate.
 * To review
 */
abstract class Event(val date: LocalDate, val confirmationAllowedDate: LocalDate){
    val guests: MutableList<User> = mutableListOf()

    /**
     * Returns a list with the guests who confirmed attendance
     */
    fun confirmedGuests(): List<User>{
        return guests.filter { guest -> guest.isConfirmedFor(this)  }
    }

    /**
     * Each subclass responsability.
     */
    abstract fun invite(unconfirmedUser: User)

    /**
     * Each subclass responsability. Returns total cost of the event
     */
    abstract fun totalCost() : Double

    /**
     *
     */
    fun confirmAssistance(guest: User){
        val today = LocalDate.now()
        if(isConfirmationAllowedFor(today)){
            guest.attendTo(this)
        }else{
            throw CannotConfirmAssitanceException("The confirmation date expired")
        }
    }


    /**
     * Encapsulates the logic about whether a date is allowed to be confirmed
     */
    private fun isConfirmationAllowedFor(today: LocalDate) =
            today.isBefore(confirmationAllowedDate) || today.isEqual(confirmationAllowedDate)

    /**
     *
     */
    fun numberOfGuests(): Int {
        return guests.size
    }

    /**
     *
     */
    fun numberOfConfirmedGuests(): Int {
        return confirmedGuests().size
    }
}
