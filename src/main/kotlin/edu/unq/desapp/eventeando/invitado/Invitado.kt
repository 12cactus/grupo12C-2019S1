package edu.unq.desapp.eventeando.invitado

import edu.unq.desapp.eventeando.cuentaCorriente.Movimiento
import edu.unq.desapp.eventeando.cuentaCorriente.TipoDeMovimiento
import edu.unq.desapp.eventeando.eventos.Evento
import edu.unq.desapp.eventeando.gasto.Gasto
import java.time.LocalDate

class Invitado {
    private var eventosConfirmados: MutableList<Evento> = mutableListOf()
    private lateinit var nombre: String
    private var movimientos: MutableList<Movimiento> = mutableListOf()

    companion object {
        fun crear(nombre: String): Invitado{
            val invitado = Invitado()
            invitado.nombre = nombre
            return invitado
        }
    }

    fun asisteA(unEvento: Evento): Boolean {
        return this.eventosConfirmados.contains(unEvento)
    }

    fun asistirA(evento: Evento) {
        if(LocalDate.now().isBefore(evento.getFechaLimite())){
            eventosConfirmados.add(evento)
        }
    }

    fun agregarGasto(gasto: Gasto, evento: Evento){
        gasto.setInvitado(this)
        evento.cargar(gasto)
    }

    fun depositar(monto: Double): Movimiento{
        var movimiento = Movimiento.crear(LocalDate.now(), monto, TipoDeMovimiento.DEPOSITO)
        movimientos.add(movimiento)
        return movimiento
    }

    fun getDepositos(): List<Movimiento>{
        return movimientos.filter { movimiento -> movimiento.tipo() == TipoDeMovimiento.DEPOSITO }
    }

    fun retirar(monto: Double): Movimiento{
        var retiro = Movimiento.crear(LocalDate.now(),monto,TipoDeMovimiento.RETIRO)
        movimientos.add(retiro)
        return retiro
    }

    fun getRetiros():List<Movimiento>{
        return movimientos.filter { movimiento -> movimiento.tipo() == TipoDeMovimiento.RETIRO }
    }

    fun getMovimientos(): MutableList<Movimiento> = movimientos

    fun getResumen(): Double {
        return  getDepositos().fold(0.00) { total, movimiento -> total + movimiento.monto() } -
                getRetiros().fold(0.00) { total, movimiento -> total + movimiento.monto() }
    }
}
