package edu.unq.desapp.eventeando.invitado

import edu.unq.desapp.eventeando.eventos.Evento

class Invitado {
    private var eventosConfirmados: List<Evento> = emptyList()
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
        val eventosConfirmadosActualizado: MutableList<Evento> = mutableListOf()
        eventosConfirmadosActualizado.addAll(this.eventosConfirmados)
        eventosConfirmadosActualizado.add(evento)
        this.eventosConfirmados = eventosConfirmadosActualizado.toList()
    }

}
