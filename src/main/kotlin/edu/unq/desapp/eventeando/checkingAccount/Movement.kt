package edu.unq.desapp.eventeando.checkingAccount

import java.time.LocalDate

class Movement {
    private var fecha: LocalDate = LocalDate.now()
    private var monto: Double = 0.00
    private var tipo: MovementType = MovementType.DEPOSITO

    companion object {
        fun crear(fecha:LocalDate, monto: Double, tipo: MovementType ): Movement {
            val movimiento = Movement()
            movimiento.monto = monto
            movimiento.fecha = fecha
            movimiento.tipo = tipo
            return movimiento
        }
    }

    fun monto():Double = monto
    /*{
        var valor = monto
        if(tipo == MovementType.RETIRO){
            valor = valor * -1
        }
        return valor
    } */

    fun tipo():MovementType = tipo
}
