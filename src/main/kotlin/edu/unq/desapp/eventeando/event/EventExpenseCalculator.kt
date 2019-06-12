package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.element.EventExpenseConfiguration
import edu.unq.desapp.eventeando.element.ShoppingItem

/**
 * Encapsulates the logic for calculate shopping list and its total, from a spends configuration
 */
class EventExpenseCalculator(private val eventExpenseConfigurations: MutableList<EventExpenseConfiguration>) {
    companion object {
        /**
         * TODO
         */
        fun create(eventExpenseConfigurations: MutableList<EventExpenseConfiguration>): EventExpenseCalculator {
            return EventExpenseCalculator(eventExpenseConfigurations)
        }
    }

    /**
     * TODO
     */
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
