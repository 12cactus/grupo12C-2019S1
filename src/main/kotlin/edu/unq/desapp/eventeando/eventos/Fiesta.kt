package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

class Fiesta : Evento
{
    private var invitados: MutableList<Invitado> = mutableListOf()
    private var fechaConfirmacion: LocalDate = LocalDate.now()

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

    override fun getFechaLimite(): LocalDate {
        return fechaConfirmacion
    }

    override fun gastos(): MutableList<Gasto> = mutableListOf()

    override fun valorTotal(): Int = this.invitadosConfirmados().size

    override fun invitados(): MutableList<Invitado> = this.invitados

    override fun invitadosConfirmados(): List<Invitado> {
        val confirmados = this.invitados.filter { invitado -> invitado.asisteA(this) }
        return confirmados
    }

    override fun invitar(usuario: Invitado) {
        this.invitados.add(usuario)
    }
}