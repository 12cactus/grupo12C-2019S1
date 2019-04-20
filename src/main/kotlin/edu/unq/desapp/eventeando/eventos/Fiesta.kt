package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

class Fiesta : Evento
{
    private var invitados: MutableList<Invitado> = mutableListOf()
    private var fechaConfirmacion: LocalDate = LocalDate.now()
    private var gastos: MutableList<Gasto> = mutableListOf()

    companion object {
        fun crear( invitados: MutableList<Invitado>, fecha: LocalDate): Fiesta {
            val fiesta = Fiesta()
            fiesta.invitados = invitados
            fiesta.fechaConfirmacion = fecha
            return fiesta
        }
    }

    public fun setFechaConfirmacion(fecha: LocalDate){
        fechaConfirmacion = fecha
    }

    override fun cargar(gasto: Gasto){
        gastos.add(gasto)
    }

    override fun getFechaLimite(): LocalDate {
        return fechaConfirmacion
    }

    override fun gastos(): MutableList<Gasto> = gastos

    override fun valorTotal(): Int {
        return invitadosConfirmados().size * gastos.fold(0) { total, gasto -> total + gasto.valor() }
    }

    override fun invitados(): MutableList<Invitado> = this.invitados

    override fun invitadosConfirmados(): List<Invitado> {
        val confirmados = this.invitados.filter { invitado -> invitado.asisteA(this) }
        return confirmados
    }

    override fun invitar(usuario: Invitado) {
        this.invitados.add(usuario)
    }

}