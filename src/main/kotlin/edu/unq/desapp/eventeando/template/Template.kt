package edu.unq.desapp.eventeando.template

import edu.unq.desapp.eventeando.element.EventExpense

/**
 * TODO
 */
class Template(val title: String, val description: String) {
    private val elements: MutableList<EventExpense> = mutableListOf()

    /**
     * TODO
     */
    fun add(element: EventExpense) {
        elements.add(element)
    }

    /**
     * TODO
     */
    fun costPerPerson(): Double {
        return elements.fold(0.00) { total, element -> element.price() + total }
    }
}
