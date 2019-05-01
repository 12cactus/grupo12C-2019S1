package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

class Baquita : Evento() {


    companion object {
        fun crear( invitados: MutableList<Invitado>, fecha: LocalDate): Baquita {
            val baquita = Baquita()
            baquita.invitados = invitados
            baquita.fechaConfirmacion = fecha
            return baquita
        }
    }



    fun gastoPorConfirmado(): Int{
        return valorTotal() / invitadosConfirmados().size
    }

    fun balanceDe(invitado: Invitado): Int{
        return totalGastosDe(invitado)-this.gastoPorConfirmado()
    }

    fun gastosDe(invitado: Invitado):List<Gasto>{
        return gastos.filter{ gasto -> gasto.generadoPor(invitado)}
    }

    fun totalGastosDe(invitado: Invitado): Int{
        return gastosDe(invitado).fold(0){total, gasto -> total + gasto.valor()}
    }
}
