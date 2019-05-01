package edu.unq.desapp.eventeando.eventos

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.util.function.Supplier

interface BaquitaContextTest: TestContext {

    fun baquita(): Baquita
    fun baquita(supplier: Supplier<Baquita>)

    fun invitado(): Invitado
    fun invitado(supplier: Supplier<Invitado>)

    fun otroInvitado(): Invitado
    fun otroInvitado(supplier: Supplier<Invitado>)

    fun gasto(): Gasto
    fun gasto(supplier: Supplier<Gasto>)

    fun otroGasto(): Gasto
    fun otroGasto(supplier: Supplier<Gasto>)

}