package edu.unq.desapp.eventeando.event


import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.element.Presentation
import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.util.function.Supplier

/**
 * Test a party event
 */
@RunWith(JavaSpecRunner::class)
class PartyTest: JavaSpec<PartyContextTest>() {

    val today = LocalDate.now()
    val weekAgo = today.minusDays (7)
    val nextWeek = today.plusDays(7)

    override fun define() {
        describe("Given a party") {
            beforeEach {
                context().party( Supplier {
                    Party(context().organizer(),
                        context().limitDateToConfirm(),
                        context().partyDate(),
                        context().guests(),
                        context().products())
                    })
                context().organizer(Supplier { "an organizer" })
                context().limitDateToConfirm(Supplier {today})
                context().partyDate(Supplier {nextWeek})
                context().products(Supplier {mutableListOf<Product>()})
            }

            describe("when invite some guests"){
                beforeEach {
                    context().guest(Supplier { Guest("guest") })
                    context().otherGuest(Supplier { Guest("otherGuest") })
                    context().guests(Supplier {mutableListOf(context().guest(), context().otherGuest())})
                }

                it("Returns the exact number of guests"){
                    assertThat(context().party().guests.size).isEqualTo(2)
                }

                describe("and add some products"){
                    beforeEach {
                        context().product(Supplier { Product("Cocacola", 90.50, Presentation("2.5L")) })
                        context().otherProduct(Supplier { Product("Pan", 50.0, Presentation("1kg")) })
                        context().products(Supplier {mutableListOf(context().product(), context().otherProduct())})
                    }

                    it("Returns the exact number of products"){
                        assertThat(context().party().products.size).isEqualTo(2)
                    }
                }

                describe("and a guest confirm assistance"){
                    it("Returns the exact number of guests"){
                        context().guest().confirmAssistanceTo(context().party())

                        assertThat(context().party().confirmedGuests().size).isEqualTo(1)
                    }
                }
            }

            describe("when the party has no guests"){
                context().guests(Supplier { mutableListOf<Guest>() })
                context().products(Supplier {mutableListOf<Product>()})

                describe("when requires total cost of party") {
                    it("returns zero") {
                        assertThat(context().party().totalCost()).isEqualTo(0.0)
                    }
                }



//                describe("sin spendings"){
//                    context().spendings(Supplier { mutableListOf<Spending>() })
//
//                    describe("cuando pedimos spendings"){
//                        it("obtenemos el cost neutro"){
//                            assertThat(context().party().totalCost()).isEqualTo(0)
//                        }
//                    }
//                }
//
//                describe("con un spending") {
//                    context().spending(Supplier { Spending(100, "sarasa") })
//
//                    describe("con guest sin confirmar") {
//                        context().guest(Supplier { Guest("guest") })
//                        context().guests(Supplier { mutableListOf(context().guest()) })
//
//                        describe("cuando pedimos los spendings") {
//                            it("obtenemos cost neutro") {
//                                context().party().load(context().spending())
//                                assertThat(context().party().totalCost()).isEqualTo(0)
//                            }
//                        }
//
//                        describe("cuando el guest confirma asistencia a la party") {
//                            describe("cuando pedimos los spendings") {
//                                it("obtenemos el cost 100") {
//                                    context().guest().attendTo(context().party())
//                                    context().party().load(context().spending())
//                                    assertThat(context().party().totalCost()).isEqualTo(100)
//                                }
//                            }
//                        }
//                    }
//                }
            }


        }

//        describe("Dado un event party con fecha caduca de confirmación") {
//            context().party(Supplier { Party("organizador", weekAgo, today) })
//
//            describe("cuando el guest confirma asistencia pasada la fecha de confirmación") {
//                context().guest(Supplier { Guest("guest") })
//                context().guests(Supplier { mutableListOf(context().guest()) })
//
//                it("obtenemos el cost nulo de guests confirmados") {
//                    context().guest().attendTo(context().party())
//                    assertThat(context().party().confirmedGuests().size).isEqualTo(0)
//                }
//            }
//        }
    }
}
