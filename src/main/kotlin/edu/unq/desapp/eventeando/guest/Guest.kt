package edu.unq.desapp.eventeando.guest

import edu.unq.desapp.eventeando.checkingAccount.Movement
import edu.unq.desapp.eventeando.checkingAccount.MovementType
import edu.unq.desapp.eventeando.event.Event
import edu.unq.desapp.eventeando.spending.Spending
import java.time.LocalDate

class Guest {
    private var eventosConfirmados: MutableList<Event> = mutableListOf()
    private lateinit var nombre: String
    private var movements: MutableList<Movement> = mutableListOf()

    companion object {
        fun crear(nombre: String): Guest{
            val invitado = Guest()
            invitado.nombre = nombre
            return invitado
        }
    }

    fun asisteA(unEvent: Event): Boolean {
        return this.eventosConfirmados.contains(unEvent)
    }

    fun asistirA(event: Event) {
        if(LocalDate.now().isBefore(event.getFechaLimite())){
            eventosConfirmados.add(event)
        }
    }

    fun agregarGasto(spending: Spending, event: Event){
        spending.setInvitado(this)
        event.cargar(spending)
    }

    fun depositar(monto: Double): Movement{
        var movimiento = Movement.crear(LocalDate.now(), monto, MovementType.DEPOSITO)
        movements.add(movimiento)
        return movimiento
    }

    fun getDepositos(): List<Movement>{
        return movements.filter { movimiento -> movimiento.tipo() == MovementType.DEPOSITO }
    }

    fun retirar(monto: Double): Movement{
        var retiro = Movement.crear(LocalDate.now(),monto,MovementType.RETIRO)
        movements.add(retiro)
        return retiro
    }

    fun getRetiros():List<Movement>{
        return movements.filter { movimiento -> movimiento.tipo() == MovementType.RETIRO }
    }

    fun getMovimientos(): MutableList<Movement> = movements

    fun getResumen(): Double {
        return  getDepositos().fold(0.00) { total, movimiento -> total + movimiento.monto() } -
                getRetiros().fold(0.00) { total, movimiento -> total + movimiento.monto() }
    }
}
