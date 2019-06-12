package edu.unq.desapp.eventeando.element

class ShoppingItem(private val amountNeeded: Int,
                   private val eventExpense: EventExpense) {
    fun total(): Double {
        return amountNeeded * eventExpense.price()
    }

    companion object {
        fun create(amountNeeded: Int, eventExpense: EventExpense): ShoppingItem {
            return ShoppingItem(amountNeeded, eventExpense)
        }
    }
}
