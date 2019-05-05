package edu.unq.desapp.eventeando.spending

import edu.unq.desapp.eventeando.guest.Guest

/**
 *
 */
class Spending(val cost: Int, val description: String) {
    lateinit var guest: Guest

    fun isFrom(aGuest: Guest): Boolean{
        return guest == aGuest
    }
}
