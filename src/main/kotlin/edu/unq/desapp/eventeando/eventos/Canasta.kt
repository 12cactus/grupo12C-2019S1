package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado

class Canasta : Evento{
    private var listaDeGastos: List<Gasto> = emptyList()
    private var invitados: List<Invitado> = emptyList()

    companion object {
        fun crear(gastos: List<Gasto>, invitados: List<Invitado>): Canasta {
            val canasta = Canasta()
            canasta.listaDeGastos = gastos
            canasta.invitados = invitados
            return canasta
        }
    }

    override fun gastos(): List<Gasto> = this.listaDeGastos

    override fun valorTotal(): Int = this.listaDeGastos.fold(0) { total, gasto -> total + gasto.valor() }

    override fun invitados(): List<Invitado> = this.invitados

    override fun invitadosConfirmados(): List<Invitado> {
        val confirmados = this.invitados.filter { invitado -> invitado.asisteA(this) }
        return confirmados
    }
}
