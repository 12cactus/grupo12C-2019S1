package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado

interface Evento{
    fun gastos(): List<Gasto>
    fun valorTotal(): Int
    fun invitados(): List<Invitado>
    fun invitadosConfirmados(): List<Invitado>
}
