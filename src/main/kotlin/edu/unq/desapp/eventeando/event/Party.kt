package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

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
