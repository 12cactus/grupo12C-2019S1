package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

class PoolMoney : Event() {


    companion object {
        fun crear(guests: MutableList<Guest>, fecha: LocalDate): PoolMoney {
            val baquita = PoolMoney()
            baquita.guests = guests
            baquita.fechaConfirmacion = fecha
            return baquita
        }
    }



    fun gastoPorConfirmado(): Int{
        return valorTotal() / invitadosConfirmados().size
    }

    fun balanceDe(guest: Guest): Int{
        return totalGastosDe(guest)-this.gastoPorConfirmado()
    }

    fun gastosDe(guest: Guest):List<Spending>{
        return spendings.filter{ gasto -> gasto.generadoPor(guest)}
    }

    fun totalGastosDe(guest: Guest): Int{
        return gastosDe(guest).fold(0){ total, gasto -> total + gasto.valor()}
    }
}
