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
class PoolMoneyTest: JavaSpec<PoolMoneyContextTest>() {
    var hoy = LocalDate.now()
    var ayer = hoy.minusDays (7)
    var mañana = hoy.plusDays(1)
    override fun define() {
        describe("dado un event PoolMoney"){
            context().poolMoney( Supplier{ PoolMoney.crear(mutableListOf<Guest>(), mañana) })

            describe("con 100 de spendings"){
                context().spending(Supplier { Spending.crear(100, "bebidas") })

                describe("con 2 guests"){
                    context().guest(Supplier { Guest.crear("Moncho") })
                    context().otherGuest(Supplier { Guest.crear("Rodolfo") })
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
                    context().guest(Supplier { Guest.crear("Moncho") })
                    context().otherGuest(Supplier { Guest.crear("Rodolfo") })

                    describe("cada guest agrega un spending distinto"){
                        context().spending(Supplier { Spending.crear(100, "bebidas") })
                        context().otherSpend(Supplier { Spending.crear(200, "tutuca") })

                        it("cuando pedimos los spendings obtenemos el cost 150"){
                            context().poolMoney().invite(context().guest())
                            context().poolMoney().invite(context().otherGuest())
                            context().guest().attendTo(context().poolMoney())
                            context().otherGuest().attendTo(context().poolMoney())
                            context().guest().addSpend(context().spending(), context().poolMoney())
                            context().otherGuest().addSpend(context().otherSpend(), context().poolMoney())

                            assertThat(context().poolMoney().costPerConfirmedGuest()).isEqualTo(150)
                        }

                        it("cuando pedimos el balance de cada guest obtenemos los valores 50 y -50"){
                            context().poolMoney().invite(context().guest())
                            context().poolMoney().invite(context().otherGuest())
                            context().guest().attendTo(context().poolMoney())
                            context().otherGuest().attendTo(context().poolMoney())
                            context().guest().addSpend(context().spending(), context().poolMoney())
                            context().otherGuest().addSpend(context().otherSpend(), context().poolMoney())

                           // assertThat(context().poolMoney().balanceOf(context().guest())).isEqualTo(-50)
                            assertThat(context().poolMoney().balanceOf(context().otherGuest())).isEqualTo(50)
                        }
                    }
                }
            }





        }
    }

}
