package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.element.EventExpenseConfiguration
import edu.unq.desapp.eventeando.element.ShoppingItem

class EventExpenseCalculator(private val eventExpenseConfigurations: MutableList<EventExpenseConfiguration>) {
    companion object {
        fun create(eventExpenseConfigurations: MutableList<EventExpenseConfiguration>): EventExpenseCalculator {
            return EventExpenseCalculator(eventExpenseConfigurations)
        }
    }

    fun calculateTotalFor(numberOfConfirmedGuests: Int): Double {
        throwsExceptionIfConfigurationsIsEmpty()
        return getShoppingList(numberOfConfirmedGuests)
                .fold(0.00) { total, shoppingItem -> total + shoppingItem.total() }
    }

    private fun throwsExceptionIfConfigurationsIsEmpty() {
        if (eventExpenseConfigurations.isEmpty()) {
            throw MissingEventExpenseConfigurationException("Cannot calculate. Event expense configuration is empty")
        }
    }

    private fun getShoppingList(confirmedGuests: Int): List<ShoppingItem> {
        return eventExpenseConfigurations.map { it.eventExpenseFor(confirmedGuests) }
    }
}
