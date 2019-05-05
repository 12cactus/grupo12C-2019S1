package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * Models an abstract event thats have spendings, guests and confirmationDate.
 * To review
 */
abstract class Event(val date: LocalDate){
    var spendings: MutableList<Spending> = mutableListOf()
    val guests: MutableList<Guest> = mutableListOf()

    /**
     * Load spend to spendings list
     */
    fun load(spending: Spending) {
        spendings.add(spending)
    }

    /**
     * Returns total costs for all spendings
     */
    open fun totalCost(): Int{
        return spendings.fold(0) { total, spending -> total + spending.cost }
    }

    /**
     * Returns a list with the guests who confirmed attendance
     */
    fun confirmedGuests(): List<Guest>{
        return guests.filter { guest -> guest.isConfirmedFor(this)  }
    }

    /**
     * Add unconfirmed guest to guests list
     */
    fun invite(unconfirmedGuest: Guest){
        this.guests.add(unconfirmedGuest)
    }
}
