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
        describe("Dado un event basket") {
            context().basket(Supplier { Basket.crear(context().spendings(), context().guests(), mañana) })

            describe("sin spendings"){
                context().spendings(Supplier { mutableListOf<Spending>() })
                context().guests(Supplier { mutableListOf<Guest>() })

                describe("cuando consultamos la lista de spendings"){
                    it("obtenemos la lista vacia") {
                        assertThat(context().basket().spends()).isEmpty()
                    }
                }

                describe("cuando consultamos el totalCost de spendings"){
                    it("obtenemos cost neutro") {
                        assertThat(context().basket().totalCost()).isEqualTo(0)
                    }
                }
            }

            describe("con spendings"){
                context().spendings(Supplier { gastos().toMutableList() })
                context().guests(Supplier { mutableListOf<Guest>() })

                describe("cuando consultamos la lista de spendings"){
                    it("obtenemos una cantidad de spendings") {
                        assertThat(context().basket().spends().count()).isEqualTo(2)
                    }
                }

                describe("cuando consultamos el totalCost de spendings"){
                    it("obtenemos la suma de cada spending") {
                        assertThat(context().basket().totalCost()).isEqualTo(10)
                    }
                }
            }

            describe("sin guests"){
                context().guests(Supplier { mutableListOf<Guest>() })
                context().spendings(Supplier { mutableListOf<Spending>() })

                describe("cuando consultamos la lista de guests"){
                    it("obtenemos una nula de guests") {
                        assertThat(context().basket().guests().count()).isEqualTo(0)
                    }
                }
            }

            describe("con guests sin confirmar"){
                context().guest(Supplier { Guest.crear("guest") })
                context().guests(Supplier { mutableListOf(context().guest(), Guest.crear("otro guest")) })
                context().spendings(Supplier { mutableListOf<Spending>() })

                describe("cuando consultamos la lista de guests"){
                    it("obtenemos 2 guests") {
                        assertThat(context().basket().guests().count()).isEqualTo(2)
                    }
                }

                it("obtenemos una lista vacia de guests confirmados") {
                    assertThat(context().basket().confirmedGuests().count()).isEqualTo(0)
                }

                describe("cuando un guest confirma asistir al event basket"){
                    it("obtenemos una lista con un guest") {
                        context().guest().attendTo(context().basket())
                        assertThat(context().basket().confirmedGuests().size).isEqualTo(1)
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

