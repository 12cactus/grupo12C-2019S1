package edu.unq.desapp.eventeando.element

class GeneralExpense(val name: String, private val price: Double) : EventExpense {
    override fun price(): Double {
        return price
    }
}
