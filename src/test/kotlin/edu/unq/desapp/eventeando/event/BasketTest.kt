package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.function.Supplier

/**
 * Test a basket event
 */
@RunWith(JavaSpecRunner::class)
class BasketTest: JavaSpec<BasketContextTest>() {
    var hoy = LocalDate.now()
    var ayer = hoy.minusDays (1)
    var mañana = hoy.plusDays(1)

    override fun define()
    {
        describe("given an basket event") {
            context().basket(Supplier { Basket.crear(context().spendings(), context().guests(), mañana) })

            describe("without spendings"){
                context().spendings(Supplier { mutableListOf<Spending>() })
                context().guests(Supplier { mutableListOf<Guest>() })

                describe("when send spends()"){
                    it("is empty") {
                        assertThat(context().basket().spends()).isEmpty()
                    }
                }

                describe("when send totalCost()"){
                    it("get neutral cost") {
                        assertThat(context().basket().totalCost()).isEqualTo(0)
                    }
                }
            }

            describe("with spendings"){
                context().spendings(Supplier { spends().toMutableList() })
                context().guests(Supplier { mutableListOf<Guest>() })

                describe("when send spends()"){
                    it("get an amount of spends") {
                        assertThat(context().basket().spends().count()).isEqualTo(2)
                    }
                }

                describe("when send totalCost()"){
                    it("get the sum of costs") {
                        assertThat(context().basket().totalCost()).isEqualTo(10)
                    }
                }
            }

            describe("without guests"){
                context().guests(Supplier { mutableListOf<Guest>() })
                context().spendings(Supplier { mutableListOf<Spending>() })

                describe("when send guests()"){
                    it("obtenemos una nula de guests") {
                        assertThat(context().basket().guests().count()).isEqualTo(0)
                    }
                }
            }

            describe("with unconfirmed guests"){
                context().guest(Supplier { Guest.crear("guest") })
                context().guests(Supplier { mutableListOf(context().guest(), Guest.crear("otro guest")) })
                context().spendings(Supplier { mutableListOf<Spending>() })

                describe("when send guests()"){
                    it("gets an amount of unconfirmed guests") {
                        assertThat(context().basket().guests().count()).isEqualTo(2)
                    }
                }

                it("gets empty confirmed lists") {
                    assertThat(context().basket().confirmedGuests().count()).isEqualTo(0)
                }

                describe("when a guest confirm asistence to an event"){
                    it("get an confirmed guests list with the guest") {
                        context().guest().attendTo(context().basket())
                        assertThat(context().basket().confirmedGuests().size).isEqualTo(1)
                    }
                }
            }
        }
    }

    private fun spends(): List<Spending> {
        val gastosDeCanasta = listOf(Spending.crear(5, "frites"), Spending.crear(5, "water"))
        return gastosDeCanasta
    }
}

