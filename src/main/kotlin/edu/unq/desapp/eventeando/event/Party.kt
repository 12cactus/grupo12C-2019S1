package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

class Party : Event() {

    companion object {
        fun crear(guests: MutableList<Guest>, fecha: LocalDate): Party {
            val fiesta = Party()
            fiesta.guests = guests
            fiesta.fechaConfirmacion = fecha
            return fiesta
        }
    }

    override fun valorTotal(): Int{
        var mult = 0
        if(invitadosConfirmados().size >0){
            mult = 1
        }
        return spendings.fold(0) { total, gasto -> total + gasto.valor() } *mult
    }

}
