package edu.unq.desapp.eventeando.event


import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.element.Commodity
import edu.unq.desapp.eventeando.element.PesentationPack
import edu.unq.desapp.eventeando.element.Presentation
import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.guest.User
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
            context().party(Supplier {
                Party(context().organizer(),
                        context().limitDateToConfirm(),
                        context().partyDate())
            })
            context().organizer(Supplier { User("an organizer") })
            context().limitDateToConfirm(Supplier { nextWeek })
            context().partyDate(Supplier { nextWeek })

            describe("whith a comodity") {
                context().commodity(Supplier { Commodity(context().product(), 1.0) })
                context().product(Supplier { Product("Cocacola", Presentation(2.5, PesentationPack.LITRE), 85.00) })

                beforeEach {
                    context().commodity()
                    context().party().addCommodity(context().commodity())
                }
                it("Returns spending total zero") {
                    assertThat(context().party().totalCost()).isEqualTo(0.0)
                }

                describe("with a guest confirmed the invitation") {
                    it("Returns spending total 85") {
                        context().guest(Supplier { User("moncho") })
                        context().party().invite(context().guest())
                        context().guest().confirmAssistanceTo(context().party())
                        assertThat(context().party().totalCost()).isEqualTo(85.0)
                    }
                }
            }

            describe("when invite some guests") {
                context().guest(Supplier { User("invitado1") })
                context().otherGuest(Supplier { User("invitado2") })

                beforeEach {
                    context().party().invite(context().guest())
                    context().party().invite(context().otherGuest())
                }

                it("Returns the exact number of guests") {
                    assertThat(context().party().numberOfGuests()).isEqualTo(2)
                }

                describe("and a guest confirm assistance") {
                    it("Returns the exact number of confirmed guests") {
                        context().guest().confirmAssistanceTo(context().party())

                        assertThat(context().party().numberOfConfirmedGuests()).isEqualTo(1)
                    }
                }
            }

            describe("when the party has no guests") {
                context().users(Supplier { mutableListOf<User>() })

                describe("when requires total cost of party") {
                    it("returns zero") {
                        assertThat(context().party().totalCost()).isEqualTo(0.0)
                    }
                }
            }

            xdescribe("with two commodities") {
                it("Returns spending total 85") {
                    xxx()
                    context().product(Supplier { Product("Cocacola", Presentation(2.5, PesentationPack.LITRE), 85.00) })
                    context().otherProduct(Supplier { Product("Pan", Presentation(0.5, PesentationPack.KILO), 50.00) })
                    context().commodity(Supplier { Commodity(context().product(), 1.0) })
                    context().otherCommodity(Supplier { Commodity(context().otherProduct(), 0.25) })
                    context().party().addCommodity(context().commodity())
                    context().party().addCommodity(context().otherCommodity())
                    context().guest(Supplier { User("moncho") })
                    context().party().invite(context().guest())
                    context().guest().confirmAssistanceTo(context().party())
                    assertThat(context().party().totalCost()).isEqualTo(97.5)
                }
            }

            describe("with two commodities and two confirmed Guest") {
                it("Returns spending total 85") {

                    context().product(Supplier { Product("Cocacola", Presentation(2.5, PesentationPack.LITRE), 85.00) })
                    context().otherProduct(Supplier { Product("Pan", Presentation(0.5, PesentationPack.KILO), 50.00) })
                    context().commodity(Supplier { Commodity(context().product(), 1.0) })
                    context().otherCommodity(Supplier { Commodity(context().otherProduct(), 0.25) })
                    context().party().addCommodity(context().commodity())
                    context().party().addCommodity(context().otherCommodity())
                    context().guest(Supplier { User("moncho") })
                    context().party().invite(context().guest())
                    context().otherGuest(Supplier { User("Dante") })
                    context().party().invite(context().otherGuest())
                    context().guest().confirmAssistanceTo(context().party())
                    context().otherGuest().confirmAssistanceTo(context().party())
                    assertThat(context().party().totalCost()).isEqualTo(195.0)
                }
            }

//            describe("given an event party with expired confirmation date") {
//                context().organizer(Supplier { User("an organizer") })
//                context().limitDateToConfirm(Supplier { weekAgo })
//                context().partyDate(Supplier { nextWeek })
//                context().party(Supplier {
//                    Party(context().organizer(),
//                            context().limitDateToConfirm(),
//                            context().partyDate())
//                })
//
//                describe("when guest confirm ") {
//                    context().organizer(Supplier { User("an organizer") })
//                    context().limitDateToConfirm(Supplier { weekAgo })
//                    context().partyDate(Supplier { nextWeek })
//
//                    context().party(Supplier {
//                        Party(context().organizer(),
//                                context().limitDateToConfirm(),
//                                context().partyDate())
//                    })
//                    context().guest(Supplier { User("moncho") })
//                    context().party().invite(context().guest())
//                    context().otherGuest(Supplier { User("Dante") })
//                    context().party().invite(context().otherGuest())
//
//                    context().guest().confirmAssistanceTo(context().party())
//
//                    it("cnnnot confirm assistance") {
//                        assertThat(context().party().confirmedGuests().size).isEqualTo(0)
//                    }
//                }
//            }

        }



    }

    private fun xxx() {

    }
}
