package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate

/**
 * Models an abstract event thats have spendings, guests and confirmationDate.
 * To review
 */
abstract class Event(val date: LocalDate){
    val guests: MutableList<User> = mutableListOf()

    /**
     * Returns a list with the guests who confirmed attendance
     */
    fun confirmedGuests(): List<User>{
        return guests.filter { guest -> guest.isConfirmedFor(this)  }
    }

    /**
     * Add unconfirmed user to guests list
     */
    abstract fun invite(unconfirmedUser: User)
}
