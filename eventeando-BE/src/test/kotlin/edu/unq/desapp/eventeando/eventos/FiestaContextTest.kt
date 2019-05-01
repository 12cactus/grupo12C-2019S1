package edu.unq.desapp.eventeando.eventos

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.util.function.Supplier


interface FiestaContextTest : TestContext{
    fun fiesta(): Fiesta
    fun fiesta(supplier: Supplier<Fiesta>)

    fun invitados(): MutableList<Invitado>
    fun invitados(supplier: Supplier<MutableList<Invitado>>)

    fun invitado(): Invitado
    fun invitado(supplier: Supplier<Invitado>)

    fun gastos(): MutableList<Gasto>
    fun gastos(supplier: Supplier<MutableList<Gasto>>)

    fun gasto(): Gasto
    fun gasto(supplier: Supplier<Gasto>)

}
