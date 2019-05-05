package edu.unq.desapp.eventeando.element

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
