package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

class Canasta : Evento(){

    companion object {
        fun crear(gastos: MutableList<Gasto>, invitados: MutableList<Invitado>, confirmacion: LocalDate): Canasta {
            val canasta = Canasta()
            canasta.gastos = gastos
            canasta.invitados = invitados
            canasta.fechaConfirmacion = confirmacion
            return canasta
        }
    }
}
