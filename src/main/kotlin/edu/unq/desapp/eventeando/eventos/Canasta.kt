package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

class Canasta : Evento{
    override fun cargar(gasto: Gasto) {
        gastos.add(gasto)
    }

    private var gastos: MutableList<Gasto> = mutableListOf()
    private var invitados: MutableList<Invitado> = mutableListOf()
    private var fechaConfirmacion: LocalDate = LocalDate.now()

    companion object {
        fun crear(gastos: MutableList<Gasto>, invitados: MutableList<Invitado>, confirmacion: LocalDate): Canasta {
            val canasta = Canasta()
            canasta.gastos = gastos
            canasta.invitados = invitados
            canasta.fechaConfirmacion = confirmacion
            return canasta
        }
    }

    override fun gastos(): MutableList<Gasto> = this.gastos

    override fun valorTotal(): Int = this.gastos.fold(0) { total, gasto -> total + gasto.valor() }

    override fun invitados(): MutableList<Invitado> = this.invitados

    override fun invitadosConfirmados(): List<Invitado> {
        val confirmados = this.invitados.filter { invitado -> invitado.asisteA(this) }
        return confirmados
    }

    override fun getFechaLimite(): LocalDate {
        return fechaConfirmacion
    }

    override fun invitar(usuario: Invitado) {
        this.invitados.add(usuario)
    }
}
