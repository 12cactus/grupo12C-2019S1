package edu.unq.desapp.eventeando.guest

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.event.Basket
import edu.unq.desapp.eventeando.event.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.function.Supplier

@RunWith(JavaSpecRunner::class)
class GuestTest: JavaSpec<GuestContextTest>() {

    override fun define()
    {
        describe("Dado un guest") {
            context().invitado(Supplier { Guest.crear("guest") })
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

    private fun canastaConInvitados(): Supplier<Event> {
        return Supplier {
            Basket.crear(mutableListOf(), mutableListOf(context().invitado(), Guest.crear("otro guest")), LocalDate.now().plusDays(1))
        }
    }
}

