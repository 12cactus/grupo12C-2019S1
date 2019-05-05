package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * Models a pool of money, thats have.....
 */
class PoolMoney : Event() {


    companion object {
        fun crear(guests: MutableList<Guest>, dateAllowedUntilConfirmation: LocalDate): PoolMoney {
            val baquita = PoolMoney()
            baquita.guests = guests
            baquita.confirmationDate = dateAllowedUntilConfirmation
            return baquita
        }
    }

    fun costPerConfirmedGuest(): Int{
        return totalCost() / confirmedGuests().size
    }

    fun balanceOf(guest: Guest): Int{
        return totalSpendsOf(guest)-this.costPerConfirmedGuest()
    }

    fun spendsOf(guest: Guest):List<Spending>{
        return spendings.filter{ spending -> spending.isFrom(guest)}
    }

    fun totalSpendsOf(guest: Guest): Int{
        return spendsOf(guest).fold(0){ total, gasto -> total + gasto.cost}
    }
}
