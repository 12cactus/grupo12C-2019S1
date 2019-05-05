package edu.unq.desapp.eventeando.guest

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.event.Basket
import edu.unq.desapp.eventeando.event.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.function.Supplier

/**
 * Tests a guest
 */
@RunWith(JavaSpecRunner::class)
class GuestTest: JavaSpec<GuestContextTest>() {

    override fun define()
    {
        describe("Dado un guest") {
            context().guest(Supplier { Guest("guest") })
            context().event(canastaConInvitados())

            describe("que no confirmó asistencia al event"){
                describe("cuando consultamos si asiste"){
                    it("obtenemos que no") {
                        assertThat(context().guest().isConfirmedFor(context().event())).isFalse()
                    }
                }
            }

            describe("que confirma asistencia al event"){
                describe("cuando consultamos si asiste"){
                    it("obtenemos que si") {
                        context().guest().attendTo(context().event())
                        assertThat(context().guest().isConfirmedFor(context().event())).isTrue()
                    }
                }
            }
        }
    }

    private fun canastaConInvitados(): Supplier<Event> {
        return Supplier {
            Basket(LocalDate.now().plusDays(1))
        }
    }
}

