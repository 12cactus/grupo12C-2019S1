package edu.unq.desapp.eventeando.spending

import edu.unq.desapp.eventeando.guest.Guest

/**
 *
 */
class Spending() {
    private var cost: Int = 0
    private var description: String = ""
    private var guest: Guest = Guest()

    companion object {
        fun crear(cost: Int, descripcion: String): Spending {
            val spending = Spending()
            spending.cost = cost
            spending.description = descripcion
            return spending
        }
    }

    fun cost(): Int = this.cost
    fun guest(): Guest = this.guest

    fun setGuest(guest: Guest){
        this.guest = guest
    }

    fun isFrom(guest: Guest): Boolean{
        return this.guest == guest
    }
}
