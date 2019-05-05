package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * Models a pool of money, thats have.....
 */
class PoolMoney(date: LocalDate) : Event(date) {

    /**
     * TODO
     */
    fun costPerConfirmedGuest(): Int{
        return totalCost() / confirmedGuests().size
    }

    /**
     * TODO
     */
    fun balanceOf(guest: Guest): Int{
        return totalSpendsOf(guest) - this.costPerConfirmedGuest()
    }

    /**
     * TODO
     */
    fun spendsOf(guest: Guest):List<Spending>{
        return spendings.filter{ spending -> spending.isFrom(guest)}
    }

    /**
     * TODO
     */
    fun totalSpendsOf(guest: Guest): Int{
        return spendsOf(guest).fold(0){ total, gasto -> total + gasto.cost}
    }
}
