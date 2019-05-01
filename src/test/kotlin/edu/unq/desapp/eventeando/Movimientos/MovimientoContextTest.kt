package edu.unq.desapp.eventeando.Movimientos

import edu.unq.desapp.eventeando.cuentaCorriente.Movimiento
import edu.unq.desapp.eventeando.eventos.Canasta
import edu.unq.desapp.eventeando.invitado.Invitado
import ar.com.dgarcia.javaspec.api.contexts.TestContext
import java.util.function.Supplier

interface MovimientoContextTest: TestContext {

    fun deposito(): Movimiento
    fun deposito(supplier: Supplier<Movimiento>)

    fun retiro(): Movimiento
    fun retiro(supplier: Supplier<Movimiento>)

    fun invitado(): Invitado
    fun invitado(supplier: Supplier<Invitado>)

    fun canastaCon1Confirmado(): Canasta
    fun canastaCon1Confirmado(supplier: Supplier<Canasta>)

}