package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

open class Evento{
    var gastos: MutableList<Gasto> = mutableListOf()
    var invitados: MutableList<Invitado> = mutableListOf()
    var fechaConfirmacion: LocalDate = LocalDate.now()

    open fun crear(fechaDeConfirmacion:LocalDate){
        fechaConfirmacion = fechaDeConfirmacion
    }

    fun gastos(): MutableList<Gasto> {
        return gastos
    }

    fun cargar(gasto: Gasto) {
        gastos.add(gasto)
    }

    fun invitados(): MutableList<Invitado> {
        return invitados
    }

    open fun valorTotal(): Int{
        return gastos.fold(0) { total, gasto -> total + gasto.valor() }
    }

    fun invitadosConfirmados(): List<Invitado>{
        return invitados.filter { invitado -> invitado.asisteA(this)  }
    }

    fun getFechaLimite(): LocalDate{
        return fechaConfirmacion
    }

    fun invitar(usuario: Invitado){
        this.invitados.add(usuario)
    }

}
