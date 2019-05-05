package edu.unq.desapp.eventeando.checkingAccount

import java.time.LocalDate

/**
 * Represents a bank movement
 */
class Movement {
    private var date: LocalDate = LocalDate.now()
    private var cost: Double = 0.00
    private var type: MovementType = MovementType.BANKDEPOSIT

    companion object {
        fun crear(date:LocalDate, cost: Double, type: MovementType ): Movement {
            val movement = Movement()
            movement.cost = cost
            movement.date = date
            movement.type = type
            return movement
        }
    }

    fun cost():Double = cost
    /*{
        var cost = cost
        if(type == MovementType.BANKWITHDRAWAL){
            cost = cost * -1
        }
        return cost
    } */

    fun type():MovementType = type
}
