package edu.unq.desapp.eventeando.event


import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.util.function.Supplier


@RunWith(JavaSpecRunner::class)
class PartyTest: JavaSpec<PartyContextTest>() {

    var hoy = LocalDate.now()
    var ayer = hoy.minusDays (7)
    var mañana = hoy.plusDays(1)

    override fun define() {
        describe("Dado un event party") {
            context().party( Supplier { Party.crear( context().guests(), mañana ) })

            describe("sin guests"){
                context().guests(Supplier { mutableListOf<Guest>() })
                
                describe("cuando pedimos los spendings") {
                    it("obtenemos cost neutro") {
                        assertThat(context().party().totalCost()).isEqualTo(0)
                    }
                }

                describe("cuando se invita a un guest"){
                    context().guest(Supplier { Guest.crear("guest") })
                    it("obtenemos la cantidad de guests el cost 1"){
                        context().party().invite(context().guest())
                        assertThat(context().party().guests().size).isEqualTo(1)
                    }
                }

                describe("sin spendings"){
                    context().spendings(Supplier { mutableListOf<Spending>() })

                    describe("cuando pedimos spendings"){
                        it("obtenemos el cost neutro"){
                            assertThat(context().party().totalCost()).isEqualTo(0)
                        }
                    }
                }

                describe("con un spending") {
                    context().spending(Supplier { Spending.crear(100, "sarasa") })

                    describe("con guest sin confirmar") {
                        context().guest(Supplier { Guest.crear("guest") })
                        context().guests(Supplier { mutableListOf(context().guest()) })

                        describe("cuando pedimos los spendings") {
                            it("obtenemos cost neutro") {
                                context().party().load(context().spending())
                                assertThat(context().party().totalCost()).isEqualTo(0)
                            }
                        }

                        describe("cuando el guest confirma asistencia a la party") {
                            describe("cuando pedimos los spendings") {
                                it("obtenemos el cost 100") {
                                    context().guest().attendTo(context().party())
                                    context().party().load(context().spending())
                                    assertThat(context().party().totalCost()).isEqualTo(100)
                                }
                            }
                        }
                    }
                }
            }
        }

        describe("Dado un event party con fecha caduca de confirmación") {
            context().party(Supplier { Party.crear(context().guests(), ayer) })

            describe("cuando el guest confirma asistencia pasada la fecha de confirmación") {
                context().guest(Supplier { Guest.crear("guest") })
                context().guests(Supplier { mutableListOf(context().guest()) })

                it("obtenemos el cost nulo de guests confirmados") {
                    context().guest().attendTo(context().party())
                    assertThat(context().party().confirmedGuests().size).isEqualTo(0)
                }
            }
        }
    }
}
