package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * Models an abstract event thats have spendings, guests and confirmationDate.
 * To review
 */
abstract class Event(val date: LocalDate){
    val spendings: MutableList<Spending> = mutableListOf()
    val guests: MutableList<Guest> = mutableListOf()

    fun load(spending: Spending) {
        spendings.add(spending)
    }

    open fun totalCost(): Int{
        return spendings.fold(0) { total, spending -> total + spending.cost }
    }

    fun confirmedGuests(): List<Guest>{
        return guests.filter { guest -> guest.isConfirmedFor(this)  }
    }

    fun invite(guest: Guest){
        this.guests.add(guest)
    }

    fun addSpend(aSpend: Spending){
        spendings.add(aSpend)
    }

    fun addGuest(unconfirmedGuest: Guest){
        guests.add(unconfirmedGuest)
    }
}
