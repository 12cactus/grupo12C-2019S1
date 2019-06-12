package edu.unq.desapp.eventeando.element

/**
 * Its the item of shopping list, thats contains the amont needed of de spend, and the spend
 */
class ShoppingItem(private val amountNeeded: Int,
                   private val eventExpense: EventExpense) {

    /**
     * TODO
     */
    fun total(): Double {
        return amountNeeded * eventExpense.price()
    }

    companion object {
        /**
         * TODO
         */
        fun create(amountNeeded: Int, eventExpense: EventExpense): ShoppingItem {
            return ShoppingItem(amountNeeded, eventExpense)
        }
    }
}
