package edu.unq.desapp.eventeando.spending

import edu.unq.desapp.eventeando.guest.Guest

class Spending() {
    private var valor: Int = 0
    private var descripcion: String = ""
    private var guest: Guest = Guest()

    companion object {
        fun crear(unValor: Int, descripcion: String): Spending {
            val gasto = Spending()
            gasto.valor = unValor
            gasto.descripcion = descripcion
            return gasto
        }
    }

    fun valor(): Int = this.valor
    fun invitado(): Guest = this.guest

    fun setInvitado(guest: Guest){
        this.guest = guest
    }

    fun generadoPor(guest: Guest): Boolean{
        return this.guest == guest
    }
}
