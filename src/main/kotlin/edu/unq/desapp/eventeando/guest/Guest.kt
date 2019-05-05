package edu.unq.desapp.eventeando.guest

import edu.unq.desapp.eventeando.User.User
import edu.unq.desapp.eventeando.checkingAccount.Movement
import edu.unq.desapp.eventeando.checkingAccount.MovementType
import edu.unq.desapp.eventeando.event.Event
import edu.unq.desapp.eventeando.spending.Spending
import java.time.LocalDate

/**
 * Models a person that has events and movements
 * a SubClass of user
 */
class Guest(): User() {
    val confirmedEvents: MutableList<Event> = mutableListOf()
    val movements: MutableList<Movement> = mutableListOf()

    /**
     * Returns true if this attends to an event
     */
    fun isConfirmedFor(anEvent: Event): Boolean {
        return this.confirmedEvents.contains(anEvent)
    }

    /**
     * Attends to an event, If the event has not happened yet
     */
    fun attendTo(event: Event) {
        if(LocalDate.now().isBefore(event.date)){
            confirmedEvents.add(event)
        }
    }

    /**
     * Add spend to spendings of this, and load the spend to event
     */
    fun addSpend(spending: Spending, event: Event){
        spending.guest = this
        event.load(spending)
    }

    /**
     *
     */
    fun putDown(cost: Double): Movement{
        val movement = Movement(LocalDate.now(), cost, MovementType.BANKDEPOSIT)
        movements.add(movement)
        return movement
    }

    /**
     * Gets bank deposits
     */
    fun getBankDeposits(): List<Movement>{
        return movements.filter { movement -> movement.type == MovementType.BANKDEPOSIT }
    }

    /**
     * Withdrawal money
     */
    fun retirar(amount: Double): Movement{
        val withdrawal = Movement(LocalDate.now(),amount,MovementType.BANKWITHDRAWAL)
        movements.add(withdrawal)
        return withdrawal
    }

    /**
     * Gets movements of withdrawal type
     */
    fun getBankWithdrawals():List<Movement>{
        return movements.filter { movement -> movement.type == MovementType.BANKWITHDRAWAL }
    }

    /**
     * Gets summary or resume of movements
     */
    fun getSummary(): Double {
        return  getBankDeposits().fold(0.00) { total, movement -> total + movement.cost } -
                getBankWithdrawals().fold(0.00) { total, movement -> total + movement.cost }
    }

    /**
     * @return true
     */
    override fun isAGuest(): Boolean = true

    /**
     * @return false
     */
    override fun isAOrganizer(): Boolean = false
}
