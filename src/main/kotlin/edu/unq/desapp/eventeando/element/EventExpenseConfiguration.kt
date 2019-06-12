package edu.unq.desapp.eventeando.element

/**
 * Represents a relation between and spend and the amount need per person of its
 */
class EventExpenseConfiguration(val eventExpense: EventExpense, val neededPerPerson: Double) {

    /**
     * TODO
     */
    fun eventExpenseFor(confirmedGuests: Int): ShoppingItem {
        return ShoppingItem.create(roundAmountNeeded(confirmedGuests), eventExpense)
    }

    private fun roundAmountNeeded(confirmedGuests: Int) = Math.ceil(confirmedGuests * neededPerPerson).toInt()
}
