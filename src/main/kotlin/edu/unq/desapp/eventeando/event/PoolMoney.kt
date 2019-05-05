package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * Models a pool of money, thats have.....
 */
class PoolMoney(date: LocalDate) : Event(date) {

    /**
     * @return cost avg per confirmed guest
     */
    fun costPerConfirmedGuest(): Int{
        return totalCost() / confirmedGuests().size
    }

    /**
     * @param guest .
     * @return balance between expenses and the cost per guest.
     */
    fun balanceOf(guest: Guest): Int{
        return totalSpendsOf(guest) - this.costPerConfirmedGuest()
    }

    /**
     * @param guest
     * @return list of guest's expenses
     */
    fun spendsOf(guest: Guest):List<Spending>{
        return spendings.filter{ spending -> spending.isFrom(guest)}
    }

    /**
     * @param guest
     * @return total amount of guest's expenses
     */
    fun totalSpendsOf(guest: Guest): Int{
        return spendsOf(guest).fold(0){ total, gasto -> total + gasto.cost}
    }
}
