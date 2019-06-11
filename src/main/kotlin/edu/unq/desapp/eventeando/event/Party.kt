package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.element.Commodity
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate

/**
 * In this type of events the expenses of the same are not distributed but
 * they are paid by the organizer. When creating the event, the organizer
 * does not just enter a list of guests to invite but also how long before
 * they are allowed confirmations
 */
class Party(val organizer: User,
            date: LocalDate,
            val confirmationAllowedDate: LocalDate,
            val shoppingList: MutableList<Commodity> = mutableListOf()): Event(date) {

    fun totalCost(): Double {
        return shoppingList
                .fold(0.00)
                { total, commoodity ->
                    total + commoodity.totalFromNeededPerPerson(numberOfConfirmedGuests())
                }
    }

    override fun  invite(user: User){
        this.guests.add(user)
        user.invitationFrom(this)
    }

    fun numberOfGuests(): Int {
        return guests.size
    }

    fun numberOfConfirmedGuests(): Int {
       return confirmedGuests().size
    }

    fun addCommodity(commodity: Commodity) {
        shoppingList.add(commodity)
    }

}
//Por el momento el organizer es un String pero esto tiene que cambiar para que sea una persona o usuario del sist
