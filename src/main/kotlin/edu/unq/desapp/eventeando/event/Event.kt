package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * Models an abstract event thats have spendings, guests and confirmationDate.
 * To review
 */
open class Event{
    var spendings: MutableList<Spending> = mutableListOf()
    var guests: MutableList<Guest> = mutableListOf()
    var confirmationDate: LocalDate = LocalDate.now()

    open fun crear(confirmationDate:LocalDate){
        this.confirmationDate = confirmationDate
    }

    fun spends(): MutableList<Spending> {
        return spendings
    }

    fun load(spending: Spending) {
        spendings.add(spending)
    }

    fun guests(): MutableList<Guest> {
        return guests
    }

    open fun totalCost(): Int{
        return spendings.fold(0) { total, gasto -> total + gasto.cost() }
    }

    fun confirmedGuests(): List<Guest>{
        return guests.filter { guest -> guest.attend(this)  }
    }

    fun confirmationDate(): LocalDate{
        return confirmationDate
    }

    fun invite(guest: Guest){
        this.guests.add(guest)
    }

}
