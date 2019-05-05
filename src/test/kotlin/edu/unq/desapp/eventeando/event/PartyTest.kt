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
        describe("Dado un evento fiesta") {
            context().fiesta( Supplier { Party.crear( context().invitados(), mañana ) })

            describe("sin guests"){
                context().invitados(Supplier { mutableListOf<Guest>() })
                
                describe("cuando pedimos los spendings") {
                    it("obtenemos valor neutro") {
                        assertThat(context().fiesta().valorTotal()).isEqualTo(0)
                    }
                }

                describe("cuando se invita a un guest"){
                    context().invitado(Supplier { Guest.crear("guest") })
                    it("obtenemos la cantidad de guests el valor 1"){
                        context().fiesta().invitar(context().invitado())
                        assertThat(context().fiesta().invitados().size).isEqualTo(1)
                    }
                }

                describe("sin spendings"){
                    context().gastos(Supplier { mutableListOf<Spending>() })

                    describe("cuando pedimos spendings"){
                        it("obtenemos el valor neutro"){
                            assertThat(context().fiesta().valorTotal()).isEqualTo(0)
                        }
                    }
                }

                describe("con un spending") {
                    context().gasto(Supplier { Spending.crear(100, "sarasa") })

                    describe("con guest sin confirmar") {
                        context().invitado(Supplier { Guest.crear("guest") })
                        context().invitados(Supplier { mutableListOf(context().invitado()) })

                        describe("cuando pedimos los spendings") {
                            it("obtenemos valor neutro") {
                                context().fiesta().cargar(context().gasto())
                                assertThat(context().fiesta().valorTotal()).isEqualTo(0)
                            }
                        }

                        describe("cuando el guest confirma asistencia a la fiesta") {
                            describe("cuando pedimos los spendings") {
                                it("obtenemos el valor 100") {
                                    context().invitado().asistirA(context().fiesta())
                                    context().fiesta().cargar(context().gasto())
                                    assertThat(context().fiesta().valorTotal()).isEqualTo(100)
                                }
                            }
                        }
                    }
                }
            }
        }

        describe("Dado un evento fiesta con fecha caduca de confirmación") {
            context().fiesta(Supplier { Party.crear(context().invitados(), ayer) })

            describe("cuando el guest confirma asistencia pasada la fecha de confirmación") {
                context().invitado(Supplier { Guest.crear("guest") })
                context().invitados(Supplier { mutableListOf(context().invitado()) })

                it("obtenemos el valor nulo de guests confirmados") {
                    context().invitado().asistirA(context().fiesta())
                    assertThat(context().fiesta().invitadosConfirmados().size).isEqualTo(0)
                }
            }
        }
    }
}
