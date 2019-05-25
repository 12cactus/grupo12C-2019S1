package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate

/**
 * Models an abstract event thats have spendings, guests and confirmationDate.
 * To review
 */
abstract class Event(val date: LocalDate){
    val spendings: MutableList<Spending> = mutableListOf()
    val users: MutableList<User> = mutableListOf()

    /**
     * Load spend to spendings list
     */
    fun load(spending: Spending) {
        spendings.add(spending)
    }

    /**
     * Returns total costs for all spendings
     */
    open fun totalCost(): Double{
        return spendings.fold(0.0) { total, spending -> total + spending.cost }
    }

    /**
     * Returns a list with the guests who confirmed attendance
     */
    fun confirmedGuests(): List<User>{
        return users.filter { guest -> guest.isConfirmedFor(this)  }
    }

    /**
     * Add unconfirmed user to guests list
     */
    fun invite(unconfirmedUser: User){
        this.users.add(unconfirmedUser)
    }
}
