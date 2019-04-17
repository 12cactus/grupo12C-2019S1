package edu.unq.desapp.eventeando.eventos

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.util.function.Supplier

interface CanastaContextTest : TestContext{
    fun canasta(): Canasta
    fun canasta(supplier: Supplier<Canasta>)

    fun gastos(): List<Gasto>
    fun gastos(supplier: Supplier<List<Gasto>>)

    fun invitados(): List<Invitado>
    fun invitados(supplier: Supplier<List<Invitado>>)

    fun invitado(): Invitado
    fun invitado(supplier: Supplier<Invitado>)
}
