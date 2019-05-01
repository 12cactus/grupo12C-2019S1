package edu.unq.desapp.eventeando.eventos

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.function.Supplier

@RunWith(JavaSpecRunner::class)
class CanastaTest: JavaSpec<CanastaContextTest>() {
    var hoy = LocalDate.now()
    var ayer = hoy.minusDays (1)
    var mañana = hoy.plusDays(1)

    override fun define()
    {
        describe("Dado un evento canasta") {
            context().canasta(Supplier { Canasta.crear(context().gastos(), context().invitados(), mañana) })

            describe("sin gastos"){
                context().gastos(Supplier { mutableListOf<Gasto>() })
                context().invitados(Supplier { mutableListOf<Invitado>() })

                describe("cuando consultamos la lista de gastos"){
                    it("obtenemos la lista vacia") {
                        assertThat(context().canasta().gastos()).isEmpty()
                    }
                }

                describe("cuando consultamos el total de gastos"){
                    it("obtenemos valor neutro") {
                        assertThat(context().canasta().valorTotal()).isEqualTo(0)
                    }
                }
            }

            describe("con gastos"){
                context().gastos(Supplier { gastos().toMutableList() })
                context().invitados(Supplier { mutableListOf<Invitado>() })

                describe("cuando consultamos la lista de gastos"){
                    it("obtenemos una cantidad de gastos") {
                        assertThat(context().canasta().gastos().count()).isEqualTo(2)
                    }
                }

                describe("cuando consultamos el total de gastos"){
                    it("obtenemos la suma de cada gasto") {
                        assertThat(context().canasta().valorTotal()).isEqualTo(10)
                    }
                }
            }

            describe("sin invitados"){
                context().invitados(Supplier { mutableListOf<Invitado>() })
                context().gastos(Supplier { mutableListOf<Gasto>() })

                describe("cuando consultamos la lista de invitados"){
                    it("obtenemos una nula de invitados") {
                        assertThat(context().canasta().invitados().count()).isEqualTo(0)
                    }
                }
            }

            describe("con invitados sin confirmar"){
                context().invitado(Supplier { Invitado.crear("invitado") })
                context().invitados(Supplier { mutableListOf(context().invitado(), Invitado.crear("otro invitado")) })
                context().gastos(Supplier { mutableListOf<Gasto>() })

                describe("cuando consultamos la lista de invitados"){
                    it("obtenemos 2 invitados") {
                        assertThat(context().canasta().invitados().count()).isEqualTo(2)
                    }
                }

                it("obtenemos una lista vacia de invitados confirmados") {
                    assertThat(context().canasta().invitadosConfirmados().count()).isEqualTo(0)
                }

                describe("cuando un invitado confirma asistir al evento canasta"){
                    it("obtenemos una lista con un invitado") {
                        context().invitado().asistirA(context().canasta())
                        assertThat(context().canasta().invitadosConfirmados().size).isEqualTo(1)
                    }
                }
            }
        }
    }

    private fun gastos(): List<Gasto> {
        val gastosDeCanasta = listOf(Gasto.crear(5, "fafafa"), Gasto.crear(5, "chicles"))
        return gastosDeCanasta
    }
}

