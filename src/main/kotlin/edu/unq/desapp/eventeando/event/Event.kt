package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

open class Event{
    var spendings: MutableList<Spending> = mutableListOf()
    var guests: MutableList<Guest> = mutableListOf()
    var fechaConfirmacion: LocalDate = LocalDate.now()

    open fun crear(fechaDeConfirmacion:LocalDate){
        fechaConfirmacion = fechaDeConfirmacion
    }

    fun gastos(): MutableList<Spending> {
        return spendings
    }

    fun cargar(spending: Spending) {
        spendings.add(spending)
    }

    fun invitados(): MutableList<Guest> {
        return guests
    }

    open fun valorTotal(): Int{
        return spendings.fold(0) { total, gasto -> total + gasto.valor() }
    }

    fun invitadosConfirmados(): List<Guest>{
        return guests.filter { invitado -> invitado.asisteA(this)  }
    }

    fun getFechaLimite(): LocalDate{
        return fechaConfirmacion
    }

    fun invitar(usuario: Guest){
        this.guests.add(usuario)
    }

}
