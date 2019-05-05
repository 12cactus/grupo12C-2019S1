package edu.unq.desapp.eventeando.guest

import edu.unq.desapp.eventeando.checkingAccount.Movement
import edu.unq.desapp.eventeando.checkingAccount.MovementType
import edu.unq.desapp.eventeando.event.Event
import edu.unq.desapp.eventeando.spending.Spending
import java.time.LocalDate

/**
 * Models a person that has events and movements
 */
class Guest(val name: String,
            val confirmedEvents: MutableList<Event> = mutableListOf(),
            val movements: MutableList<Movement> = mutableListOf()) {

    fun attend(anEvent: Event): Boolean {
        return this.confirmedEvents.contains(anEvent)
    }

    fun attendTo(event: Event) {
        if(LocalDate.now().isBefore(event.confirmationDate())){
            confirmedEvents.add(event)
        }
    }

    fun addSpend(spending: Spending, event: Event){
        spending.setGuest(this)
        event.load(spending)
    }

    fun putDown(cost: Double): Movement{
        val movement = Movement(LocalDate.now(), cost, MovementType.BANKDEPOSIT)
        movements.add(movement)
        return movement
    }

    fun getBankDeposits(): List<Movement>{
        return movements.filter { movement -> movement.type == MovementType.BANKDEPOSIT }
    }

    fun retirar(amount: Double): Movement{
        val withdrawal = Movement(LocalDate.now(),amount,MovementType.BANKWITHDRAWAL)
        movements.add(withdrawal)
        return withdrawal
    }

    fun getBankWithdrawals():List<Movement>{
        return movements.filter { movement -> movement.type == MovementType.BANKWITHDRAWAL }
    }

    fun getSummary(): Double {
        return  getBankDeposits().fold(0.00) { total, movement -> total + movement.cost } -
                getBankWithdrawals().fold(0.00) { total, movement -> total + movement.cost }
    }
}
