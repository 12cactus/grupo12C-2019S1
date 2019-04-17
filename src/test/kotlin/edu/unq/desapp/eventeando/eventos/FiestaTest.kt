package edu.unq.desapp.eventeando.eventos


import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate


import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.util.function.Supplier


@RunWith(JavaSpecRunner::class)
class FiestaTest: JavaSpec<FiestaContextTest>() {

    var hoy = LocalDate.now()
    var ayer = hoy.minusDays (7)
    var mañana = hoy.plusDays(1)

    override fun define() {
        describe("Dado un evento fiesta") {
            context().fiesta( Supplier { Fiesta.crear( context().invitados(), mañana ) })

            describe("sin invitados"){
                context().invitados(Supplier { emptyList<Invitado>().toMutableList() })
                
                describe("cuando pedimos los gastos") {
                    it("obtenemos valor neutro") {
                        assertThat(context().fiesta().valorTotal()).isEqualTo(0)
                    }
                }

                describe("cuando se invita a un invitado"){
                    context().invitado(Supplier { Invitado.crear("invitado") })
                    it("obtenemos la cantidad de invitados el valor 1"){
                        context().fiesta().invitar(context().invitado())
                        assertThat(context().fiesta().invitados().size).isEqualTo(1)
                    }
                }
            }

            describe("con invitado sin confirmar") {
                context().invitado(Supplier { Invitado.crear("invitado") })
                context().invitados(Supplier { mutableListOf(context().invitado()) })

                describe("cuando pedimos los gastos") {
                    it("obtenemos valor neutro") {
                        assertThat(context().fiesta().valorTotal()).isEqualTo(0)
                    }
                }

                describe("cuando el invitado confirma asistencia a la fiesta") {
                    describe("cuando pedimos los gastos") {
                        it("obtenemos el valor 1") {
                            context().invitado().asistirA(context().fiesta())
                            assertThat(context().fiesta().valorTotal()).isEqualTo(1)
                        }
                    }
                }
            }
        }

        describe("Dado un evento fiesta") {
            context().fiesta(Supplier { Fiesta.crear(context().invitados(), ayer) })

            describe("cuando el invitado confirma asistencia pasada la fecha de confirmación") {
                context().invitado(Supplier { Invitado.crear("invitado") })
                context().invitados(Supplier { mutableListOf(context().invitado()) })

                it("obtenemos el valor nulo de invitados confirmados") {
                    context().invitado().asistirA(context().fiesta())
                    assertThat(context().fiesta().invitadosConfirmados().size).isEqualTo(0)
                }

            }
        }


    }
}