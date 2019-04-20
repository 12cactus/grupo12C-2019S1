package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

interface Evento{
    fun gastos(): MutableList<Gasto>
    fun valorTotal(): Int
    fun invitados(): MutableList<Invitado>
    fun invitadosConfirmados(): List<Invitado>
    fun getFechaLimite(): LocalDate
    fun invitar(usuario: Invitado)
    fun cargar(gasto: Gasto)
}
