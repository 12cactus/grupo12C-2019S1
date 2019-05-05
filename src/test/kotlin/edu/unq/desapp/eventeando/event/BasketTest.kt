package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.function.Supplier

@RunWith(JavaSpecRunner::class)
class BasketTest: JavaSpec<BasketContextTest>() {
    var hoy = LocalDate.now()
    var ayer = hoy.minusDays (1)
    var mañana = hoy.plusDays(1)

    override fun define()
    {
        describe("Dado un evento canasta") {
            context().canasta(Supplier { Basket.crear(context().gastos(), context().invitados(), mañana) })

            describe("sin spendings"){
                context().gastos(Supplier { mutableListOf<Spending>() })
                context().invitados(Supplier { mutableListOf<Guest>() })

                describe("cuando consultamos la lista de spendings"){
                    it("obtenemos la lista vacia") {
                        assertThat(context().canasta().gastos()).isEmpty()
                    }
                }

                describe("cuando consultamos el total de spendings"){
                    it("obtenemos valor neutro") {
                        assertThat(context().canasta().valorTotal()).isEqualTo(0)
                    }
                }
            }

            describe("con spendings"){
                context().gastos(Supplier { gastos().toMutableList() })
                context().invitados(Supplier { mutableListOf<Guest>() })

                describe("cuando consultamos la lista de spendings"){
                    it("obtenemos una cantidad de spendings") {
                        assertThat(context().canasta().gastos().count()).isEqualTo(2)
                    }
                }

                describe("cuando consultamos el total de spendings"){
                    it("obtenemos la suma de cada spending") {
                        assertThat(context().canasta().valorTotal()).isEqualTo(10)
                    }
                }
            }

            describe("sin guests"){
                context().invitados(Supplier { mutableListOf<Guest>() })
                context().gastos(Supplier { mutableListOf<Spending>() })

                describe("cuando consultamos la lista de guests"){
                    it("obtenemos una nula de guests") {
                        assertThat(context().canasta().invitados().count()).isEqualTo(0)
                    }
                }
            }

            describe("con guests sin confirmar"){
                context().invitado(Supplier { Guest.crear("guest") })
                context().invitados(Supplier { mutableListOf(context().invitado(), Guest.crear("otro guest")) })
                context().gastos(Supplier { mutableListOf<Spending>() })

                describe("cuando consultamos la lista de guests"){
                    it("obtenemos 2 guests") {
                        assertThat(context().canasta().invitados().count()).isEqualTo(2)
                    }
                }

                it("obtenemos una lista vacia de guests confirmados") {
                    assertThat(context().canasta().invitadosConfirmados().count()).isEqualTo(0)
                }

                describe("cuando un guest confirma asistir al evento canasta"){
                    it("obtenemos una lista con un guest") {
                        context().invitado().asistirA(context().canasta())
                        assertThat(context().canasta().invitadosConfirmados().size).isEqualTo(1)
                    }
                }
            }
        }
    }

    private fun gastos(): List<Spending> {
        val gastosDeCanasta = listOf(Spending.crear(5, "fafafa"), Spending.crear(5, "chicles"))
        return gastosDeCanasta
    }
}

