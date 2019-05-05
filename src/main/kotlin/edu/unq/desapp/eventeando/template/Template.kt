package edu.unq.desapp.eventeando.template

import edu.unq.desapp.eventeando.element.Element

/**
 *
 */
class Template(val title: String, val description: String) {
    private val elements:  MutableList<Element> = mutableListOf()

    fun add(element: Element){
        elements.add(element)
    }

    fun costPerPerson():Double{
        return elements.fold(0.00){ total, element -> element.proportionPerPerson + total}
    }
}
