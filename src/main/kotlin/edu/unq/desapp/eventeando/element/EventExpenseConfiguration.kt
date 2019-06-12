package edu.unq.desapp.eventeando.element

class EventExpenseConfiguration(val eventExpense: EventExpense, val neededPerPerson: Double) {
    fun eventExpenseFor(confirmedGuests: Int): ShoppingItem {
        return ShoppingItem.create(roundAmountNeeded(confirmedGuests), eventExpense)
    }

    private fun roundAmountNeeded(confirmedGuests: Int) = Math.ceil(confirmedGuests * neededPerPerson).toInt()
}
