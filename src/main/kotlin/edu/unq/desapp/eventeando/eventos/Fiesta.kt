package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

class Fiesta : Evento() {

    companion object {
        fun crear( invitados: MutableList<Invitado>, fecha: LocalDate): Fiesta {
            val fiesta = Fiesta()
            fiesta.invitados = invitados
            fiesta.fechaConfirmacion = fecha
            return fiesta
        }
    }

    override fun valorTotal(): Int{
        var mult = 0
        if(invitadosConfirmados().size >0){
            mult = 1
        }
        return gastos.fold(0) { total, gasto -> total + gasto.valor() } *mult
    }

}