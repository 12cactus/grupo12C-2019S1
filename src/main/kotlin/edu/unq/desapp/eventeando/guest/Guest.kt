package edu.unq.desapp.eventeando.guest

import edu.unq.desapp.eventeando.checkingAccount.Movement
import edu.unq.desapp.eventeando.checkingAccount.MovementType
import edu.unq.desapp.eventeando.event.Event
import edu.unq.desapp.eventeando.spending.Spending
import java.time.LocalDate

/**
 * Models a person that has events and movements
 */
class Guest {
    private var confirmedEvents: MutableList<Event> = mutableListOf()
    private lateinit var name: String
    private var movements: MutableList<Movement> = mutableListOf()

    companion object {
        fun crear(name: String): Guest{
            val guest = Guest()
            guest.name = name
            return guest
        }
    }

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
        var movement = Movement.crear(LocalDate.now(), cost, MovementType.BANKDEPOSIT)
        movements.add(movement)
        return movement
    }

    fun getBankDeposits(): List<Movement>{
        return movements.filter { movement -> movement.type() == MovementType.BANKDEPOSIT }
    }

    fun retirar(amount: Double): Movement{
        var withdrawal = Movement.crear(LocalDate.now(),amount,MovementType.BANKWITHDRAWAL)
        movements.add(withdrawal)
        return withdrawal
    }

    fun getBankWithdrawals():List<Movement>{
        return movements.filter { movement -> movement.type() == MovementType.BANKWITHDRAWAL }
    }

    fun getMovements(): MutableList<Movement> = movements

    fun getSummary(): Double {
        return  getBankDeposits().fold(0.00) { total, movement -> total + movement.cost() } -
                getBankWithdrawals().fold(0.00) { total, movement -> total + movement.cost() }
    }
}
