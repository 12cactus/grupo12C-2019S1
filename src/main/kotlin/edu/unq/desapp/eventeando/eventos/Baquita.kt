package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

class Baquita : Evento{

    private var gastos: MutableList<Gasto> = mutableListOf()
    private var invitados: MutableList<Invitado> = mutableListOf()
    private var fechaConfirmacion: LocalDate = LocalDate.now()

    companion object {
        fun crear( invitados: MutableList<Invitado>, fecha: LocalDate): Baquita {
            val baquita = Baquita()
            baquita.invitados = invitados
            baquita.fechaConfirmacion = fecha
            return baquita
        }
    }

    override fun invitados(): MutableList<Invitado> {
        return invitados
    }

    override fun gastos(): MutableList<Gasto> {
        return gastos
    }

    override  fun valorTotal(): Int{
        return gastos.fold(0) { total, gasto -> total + gasto.valor() }
    }

    override fun invitadosConfirmados(): List<Invitado>{
        return invitados.filter { invitado -> invitado.asisteA(this)  }
    }

    override fun getFechaLimite(): LocalDate{
        return fechaConfirmacion
    }

    override fun invitar(usuario: Invitado){
        this.invitados.add(usuario)
    }

    override fun cargar(gasto: Gasto) {
        gastos.add(gasto)
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
