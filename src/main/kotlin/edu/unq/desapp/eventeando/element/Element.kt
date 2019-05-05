package edu.unq.desapp.eventeando.element

/**
 * Model things that can be added to an event and have a cost and description
 */
class Element {
    var description: String = ""
    var proportionPerPerson: Double = 0.00

    companion object {
        fun crear(description:String, proportionPerPerson: Double ): Element {
            val element = Element()
            element.description=description
            element.proportionPerPerson=proportionPerPerson
            return element
        }
    }

    fun proportionPerPerson():Double{
        return proportionPerPerson
    }
}
