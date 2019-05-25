package edu.unq.desapp.eventeando.event
import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.element.Presentation
import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.util.function.Supplier

/**
 * Test a pool money event
 */
@RunWith(JavaSpecRunner::class)
class PoolMoneyTest: JavaSpec<PoolMoneyContextTest>() {
    var hoy = LocalDate.now()
    var ayer = hoy.minusDays (7)
    var mañana = hoy.plusDays(1)
    override fun define() {
        describe("dado un event PoolMoney"){
            context().poolMoney( Supplier{ PoolMoney(mañana) })

            describe("con 100 de spendings"){
                context().spending(Supplier { Spending(100.0, Product("as", Presentation("s"))) })

                describe("con 2 guests"){
                    context().guest(Supplier { User("Moncho") })
                    context().otherGuest(Supplier { User("Rodolfo") })
                    describe("cuando pedimos el spending"){
                        it("obtenemos el cost 50"){
                            context().poolMoney().load( context().spending() )
                            context().poolMoney().invite(context().guest())
                            context().poolMoney().invite(context().otherGuest())
                            context().guest().attendTo(context().poolMoney())
                            context().otherGuest().attendTo(context().poolMoney())

                            assertThat( context().poolMoney().costPerConfirmedGuest()).isEqualTo(50)
                        }
                    }
                }
            }
            describe("sin spendings"){
                describe("con 2 guests") {
                    context().guest(Supplier { User("Moncho") })
                    context().otherGuest(Supplier { User("Rodolfo") })

                    describe("cada user agrega un spending distinto"){
                        context().spending(Supplier { Spending(100.0, Product("as", Presentation("s"))) })
                        context().otherSpend(Supplier { Spending(100.0, Product("as", Presentation("s"))) })

                        it("cuando pedimos los spendings obtenemos el cost 150"){
                            context().poolMoney().invite(context().guest())
                            context().poolMoney().invite(context().otherGuest())
                            context().guest().attendTo(context().poolMoney())
                            context().otherGuest().attendTo(context().poolMoney())
                            context().guest().addSpend(context().spending(), context().poolMoney())
                            context().otherGuest().addSpend(context().otherSpend(), context().poolMoney())

                            assertThat(context().poolMoney().costPerConfirmedGuest()).isEqualTo(150)
                        }

                        it("cuando pedimos el balance de cada user obtenemos los valores 50 y -50"){
                            context().poolMoney().invite(context().guest())
                            context().poolMoney().invite(context().otherGuest())
                            context().guest().attendTo(context().poolMoney())
                            context().otherGuest().attendTo(context().poolMoney())
                            context().guest().addSpend(context().spending(), context().poolMoney())
                            context().otherGuest().addSpend(context().otherSpend(), context().poolMoney())

                            assertThat(context().poolMoney().balanceOf(context().otherGuest())).isEqualTo(50)
                        }
                    }
                }
            }





        }
    }

}
