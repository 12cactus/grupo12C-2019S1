package edu.unq.desapp.eventeando.gasto

import edu.unq.desapp.eventeando.invitado.Invitado

class Gasto() {
    private var valor: Int = 0
    private var descripcion: String = ""
    private var invitado: Invitado = Invitado()

    companion object {
        fun crear(unValor: Int, descripcion: String): Gasto {
            val gasto = Gasto()
            gasto.valor = unValor
            gasto.descripcion = descripcion
            return gasto
        }
    }

    fun valor(): Int = this.valor
    fun invitado(): Invitado = this.invitado

    fun setInvitado(invitado: Invitado){
        this.invitado = invitado
    }

    fun generadoPor(invitado: Invitado): Boolean{
        return this.invitado == invitado
    }
}
