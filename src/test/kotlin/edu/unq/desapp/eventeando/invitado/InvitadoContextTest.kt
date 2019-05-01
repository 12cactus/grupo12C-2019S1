package edu.unq.desapp.eventeando.invitado

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.eventos.Evento
import java.util.function.Supplier

interface InvitadoContextTest : TestContext{
    fun invitado(): Invitado
    fun invitado(supplier: Supplier<Invitado>)

    fun evento(): Evento
    fun evento(supplier: Supplier<Evento>)
}
