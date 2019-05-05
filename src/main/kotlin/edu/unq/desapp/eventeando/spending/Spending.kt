package edu.unq.desapp.eventeando.spending

import edu.unq.desapp.eventeando.guest.Guest

/**
 * TODO
 */
class Spending(val cost: Int, val description: String) {
    lateinit var guest: Guest

    /**
     * Returns true if this corresponds to the guest received by parameter
     */
    fun isFrom(aGuest: Guest): Boolean{
        return guest == aGuest
    }
}
