package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.guest.Guest
import edu.unq.desapp.eventeando.spending.Spending
import java.time.LocalDate

/**
 * Models a putlock
 */
class Basket(date: LocalDate) : Event(date){

    val selectSpendings: MutableList<Spending> = mutableListOf()

    fun selectSpendBy(aSpend: Spending, aGuest: Guest){
        var selectSpend = this.spendings.filter { spend ->
                                spend.description == aSpend.description &&
                                spend.cost == aSpend.cost
                                }[0]
        selectSpend.guest = aGuest
        this.selectSpendings.add(selectSpend)
        this.spendings.remove(aSpend)
    }

}
