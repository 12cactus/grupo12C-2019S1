package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

class Basket : Event(){

    companion object {
        fun crear(spendings: MutableList<Spending>, guests: MutableList<Guest>, confirmacion: LocalDate): Basket {
            val canasta = Basket()
            canasta.spendings = spendings
            canasta.guests = guests
            canasta.fechaConfirmacion = confirmacion
            return canasta
        }
    }
}
