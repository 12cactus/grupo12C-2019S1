package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * In this type of events the expenses of the same are not distributed but
 * they are paid by the organizer. When creating the event, the organizer
 * does not just enter a list of users to invite but also how long before
 * they are allowed confirmations
 */
class Party(val organizer: String,
            val confirmationAllowedDate: LocalDate,
            val date: LocalDate,
            val guests: MutableList<Guest> = mutableListOf(),
            val products: MutableList<Product> = mutableListOf()) {

    fun totalCost(): Double {
        return products.fold(0.0) { total, product -> total + product.cost }
    }

    fun invite(guest: Guest){
        this.guests.add(guest)
        guest.invitationFrom(this)
    }

    fun confirmedGuests(): List<Guest>{
        return guests.filter { guest -> guest.isConfirmedTo(this)  }
    }
}
//Por el momento el organizer es un String pero esto tiene que cambiar para que sea una persona o usuario del sist
