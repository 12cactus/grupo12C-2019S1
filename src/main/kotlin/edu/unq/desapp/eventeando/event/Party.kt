package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.guest.User
import edu.unq.desapp.eventeando.spending.Spending
import java.time.LocalDate

/**
 * In this type of events the expenses of the same are not distributed but
 * they are paid by the organizer. When creating the event, the organizer
 * does not just enter a list of guests to invite but also how long before
 * they are allowed confirmations
 */
class Party(val organizer: User,
            val confirmationAllowedDate: LocalDate,
            val date: LocalDate,
            val guests: MutableList<User> = mutableListOf(),
            val shoppingList: MutableList<Spending> = mutableListOf(),
            val amountsNeeded: MutableList<AmountNeeded> = mutableListOf()) {

    fun totalCost(): Double {
        return shoppingList.fold(0.0) { total, spending -> total + spending.cost }
    }

    fun invite(user: User){
        this.guests.add(user)
        user.invitationFrom(this)
    }

    fun confirmedGuests(): List<User>{
        return guests.filter { guest -> guest.isConfirmedTo(this)  }
    }

    fun numberOfGuests(): Int {
        return guests.size
    }

    fun numberOfConfirmedGuests(): Int {
       return confirmedGuests().size
    }

    fun numberOfSSpendings(): Int {
        return shoppingList.size
    }


}
//Por el momento el organizer es un String pero esto tiene que cambiar para que sea una persona o usuario del sist
