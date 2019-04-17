package edu.unq.desapp.eventeando.eventos

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.util.function.Supplier

@RunWith(JavaSpecRunner::class)
class CanastaTest: JavaSpec<CanastaContextTest>() {

    override fun define()
    {
        describe("Dado un evento canasta") {
            context().canasta(Supplier { Canasta.crear(context().gastos(), context().invitados()) })

            describe("sin gastos"){
                context().gastos(Supplier { emptyList<Gasto>() })
                context().invitados(Supplier { emptyList<Invitado>() })

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
                context().gastos(Supplier { gastos() })
                context().invitados(Supplier { emptyList<Invitado>() })

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
                context().invitados(Supplier { emptyList<Invitado>() })
                context().gastos(Supplier { emptyList<Gasto>() })

                describe("cuando consultamos la lista de invitados"){
                    it("obtenemos una nula de invitados") {
                        assertThat(context().canasta().invitados().count()).isEqualTo(0)
                    }
                }
            }

            describe("con invitados sin confirmar"){
                context().invitado(Supplier { Invitado.crear("invitado") })
                context().invitados(Supplier { listOf(context().invitado(), Invitado.crear("otro invitado")) })
                context().gastos(Supplier { emptyList<Gasto>() })

                describe("cuando consultamos la lista de invitados"){
                    it("obtenemos 2 invitados") {
                        assertThat(context().canasta().invitados().count()).isEqualTo(2)
                    }
                }

                it("obtenemos una lista vacia de invitados confirmados") {
                    assertThat(context().canasta().invitadosConfirmados().count()).isEqualTo(0)
                }

                describe("cuando un invitado confirma asistir al evento canasta"){
                    describe("si consultamos los invitados confirmados") {
                        it("obtenemos una lista con un invitado") {
                            context().invitado().asistirA(context().canasta())
                            assertThat(context().canasta().invitadosConfirmados().count()).isEqualTo(1)
                        }
                    }
                }
            }
        }
    }

    private fun gastos(): List<Gasto> {
        val gastosDeCanasta = listOf(Gasto.crear(5), Gasto.crear(5))
        return gastosDeCanasta
    }
}

