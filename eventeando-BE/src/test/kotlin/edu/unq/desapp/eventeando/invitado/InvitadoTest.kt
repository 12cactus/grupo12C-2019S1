package edu.unq.desapp.eventeando.invitado

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.eventos.Canasta
import edu.unq.desapp.eventeando.eventos.Evento
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.util.function.Supplier

@RunWith(JavaSpecRunner::class)
class InvitadoTest: JavaSpec<InvitadoContextTest>() {

    override fun define()
    {
        describe("Dado un invitado") {
            context().invitado(Supplier { Invitado.crear("invitado") })
            context().evento(canastaConInvitados())

            describe("que no confirmó asistencia al evento"){
                describe("cuando consultamos si asiste"){
                    it("obtenemos que no") {
                        assertThat(context().invitado().asisteA(context().evento())).isFalse()
                    }
                }
            }

            describe("que confirma asistencia al evento"){
                describe("cuando consultamos si asiste"){
                    it("obtenemos que si") {
                        context().invitado().asistirA(context().evento())
                        assertThat(context().invitado().asisteA(context().evento())).isTrue()
                    }
                }
            }
        }
    }

    private fun canastaConInvitados(): Supplier<Evento> {
        return Supplier {
            Canasta.crear(emptyList(), listOf(context().invitado(), Invitado.crear("otro invitado")))
        }
    }
}

