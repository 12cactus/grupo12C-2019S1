package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * In this type of events the expenses of the same are not distributed but
 * they are paid by the organizer. When creating the event, the organizer
 * does not just enter a list of users to invite but also how long before
 * they are allowed confirmations
 */
class Party : Event() {

    companion object {
        fun crear(guests: MutableList<Guest>, date: LocalDate): Party {
            val party = Party()
            party.guests = guests
            party.confirmationDate = date
            return party
        }
    }

    override fun totalCost(): Int{
        var mult = 0
        if(confirmedGuests().size >0){
            mult = 1
        }
        return spendings.fold(0) { total, gasto -> total + gasto.cost() } *mult
    }

}
