package edu.unq.desapp.eventeando.cuentaCorriente

import java.time.LocalDate

class Movimiento {
    private var fecha: LocalDate = LocalDate.now()
    private var monto: Double = 0.00
    private var tipo: TipoDeMovimiento = TipoDeMovimiento.DEPOSITO

    companion object {
        fun crear(fecha:LocalDate, monto: Double, tipo: TipoDeMovimiento ): Movimiento {
            val movimiento = Movimiento()
            movimiento.monto = monto
            movimiento.fecha = fecha
            movimiento.tipo = tipo
            return movimiento
        }
    }

    fun monto():Double = monto
    /*{
        var valor = monto
        if(tipo == TipoDeMovimiento.RETIRO){
            valor = valor * -1
        }
        return valor
    } */

    fun tipo():TipoDeMovimiento = tipo
}