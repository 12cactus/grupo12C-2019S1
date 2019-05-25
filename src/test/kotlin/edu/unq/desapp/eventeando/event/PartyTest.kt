package edu.unq.desapp.eventeando.event


import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.element.Presentation
import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.guest.User
import edu.unq.desapp.eventeando.spending.Spending
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
                context().party(Supplier {
                    Party(context().organizer(),
                            context().limitDateToConfirm(),
                            context().partyDate(),
                            context().users(),
                            context().spendings())
                })
                context().organizer(Supplier { User("an organizer") })
                context().limitDateToConfirm(Supplier { today })
                context().partyDate(Supplier { nextWeek })
                context().spendings(Supplier { mutableListOf<Spending>() })
                context().users(Supplier { mutableListOf<User>() })
            }

            describe("when invite some guests") {
                beforeEach {
                    context().guest(Supplier { User("invitado1") })
                    context().otherGuest(Supplier { User("invitado2") })
                    context().users(Supplier { mutableListOf(context().guest(), context().otherGuest()) })
                }

                it("Returns the exact number of guests") {
                    assertThat(context().party().numberOfGuests()).isEqualTo(2)
                }

                describe("and a guest confirm assistance") {
                    it("Returns the exact number of guests") {
                        context().guest().confirmAssistanceTo(context().party())

                        assertThat(context().party().numberOfConfirmedGuests()).isEqualTo(1)
                    }
                }
            }

            describe("when the party has no guests") {
                context().users(Supplier { mutableListOf<User>() })
                context().spendings(Supplier { mutableListOf<Spending>() })

                describe("when requires total cost of party") {
                    it("returns zero") {
                        assertThat(context().party().totalCost()).isEqualTo(0.0)
                    }
                }
            }

            describe("when add some amount needed for guest") {
                beforeEach {
                    context().product(Supplier { Product("Cocacola", Presentation("2.5L")) })
                    context().otherProduct(Supplier { Product("Pan", Presentation("1kg")) })
                    context().spending(Supplier { Spending(12.12, context().product()) })
                    context().otherSpending(Supplier { Spending(12.12, context().otherProduct()) })
                    context().spendings(Supplier { mutableListOf(context().spending(), context().otherSpending()) })
                }

                it("Returns the exact number of spendings") {
                    assertThat(context().party().numberOfSSpendings()).isEqualTo(2)
                }

                it("Returns spending total") {
                    assertThat(context().party().totalCost()).isEqualTo(24.24)
                }
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
//                    describe("con user sin confirmar") {
//                        context().user(Supplier { User("user") })
//                        context().guests(Supplier { mutableListOf(context().user()) })
//
//                        describe("cuando pedimos los spendings") {
//                            it("obtenemos cost neutro") {
//                                context().party().load(context().spending())
//                                assertThat(context().party().totalCost()).isEqualTo(0)
//                            }
//                        }
//
//                        describe("cuando el user confirma asistencia a la party") {
//                            describe("cuando pedimos los spendings") {
//                                it("obtenemos el cost 100") {
//                                    context().user().attendTo(context().party())
//                                    context().party().load(context().spending())
//                                    assertThat(context().party().totalCost()).isEqualTo(100)
//                                }
//                            }
//                        }
//                    }
//                }





//        describe("Dado un event party con fecha caduca de confirmación") {
//            context().party(Supplier { Party("organizador", weekAgo, today) })
//
//            describe("cuando el user confirma asistencia pasada la fecha de confirmación") {
//                context().user(Supplier { User("user") })
//                context().guests(Supplier { mutableListOf(context().user()) })
//
//                it("obtenemos el cost nulo de guests confirmados") {
//                    context().user().attendTo(context().party())
//                    assertThat(context().party().confirmedGuests().size).isEqualTo(0)
//                }
//            }
//        }
    }
}
