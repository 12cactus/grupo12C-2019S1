package edu.unq.desapp.eventeando.spending

import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.guest.User

/**
 * TODO
 */
class Spending(val cost: Double, val product: Product) {
    lateinit var user: User

    /**
     * Returns true if this corresponds to the user received by parameter
     */
    fun isFrom(aUser: User): Boolean{
        return user == aUser
    }
}
