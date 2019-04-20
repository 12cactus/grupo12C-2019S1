package edu.unq.desapp.eventeando.invitado

import edu.unq.desapp.eventeando.eventos.Evento
import edu.unq.desapp.eventeando.gasto.Gasto
import java.time.LocalDate

class Invitado {
    private var eventosConfirmados: MutableList<Evento> = mutableListOf()
    private lateinit var nombre: String

    companion object {
        fun crear(nombre: String): Invitado{
            val invitado = Invitado()
            invitado.nombre = nombre
            return invitado
        }
    }

    fun asisteA(unEvento: Evento): Boolean {
        return this.eventosConfirmados.contains(unEvento)
    }

    fun asistirA(evento: Evento) {
        if(LocalDate.now().isBefore(evento.getFechaLimite())){
            eventosConfirmados.add(evento)
        }
    }

    fun agregarGasto(gasto: Gasto, evento: Evento){
        gasto.setInvitado(this)
        evento.cargar(gasto)
    }

}
