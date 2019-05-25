package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate

/**
 * Models a pool of money, thats have.....
 */
class PoolMoney(date: LocalDate) : Event(date) {

    /**
     * TODO
     */
    fun costPerConfirmedGuest(): Double{
        return totalCost() / confirmedGuests().size
    }

    /**
     * TODO
     */
    fun balanceOf(user: User): Double{
        return totalSpendsOf(user) - this.costPerConfirmedGuest()
    }

    /**
     * TODO
     */
    fun spendsOf(user: User):List<Spending>{
        return spendings.filter{ spending -> spending.isFrom(user)}
    }

    /**
     * TODO
     */
    fun totalSpendsOf(user: User): Double{
        return spendsOf(user).fold(0.0){ total, gasto -> total + gasto.cost}
    }
}
