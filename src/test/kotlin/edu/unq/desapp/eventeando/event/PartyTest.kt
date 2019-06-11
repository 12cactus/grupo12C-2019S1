package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.element.Commodity
import edu.unq.desapp.eventeando.element.Presentation
import edu.unq.desapp.eventeando.element.PresentationPack
import edu.unq.desapp.eventeando.element.Product
import edu.unq.desapp.eventeando.guest.User
import edu.unq.desapp.eventeando.guest.exception.CannotConfirmAssitanceException
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.function.Supplier


/**
 * Test a party event
 */
@RunWith(JavaSpecRunner::class)
class PartyTest : JavaSpec<PartyContextTest>() {

    private val today = LocalDate.now()
    private val weekAgo = today.minusDays(7)
    private val nextWeek = today.plusDays(7)

    override fun define() {
        describe("Given a party") {
            context().party(Supplier {
                Party(context().organizer(),
                        context().partyDate(),
                        context().limitDateToConfirm())
            })
            context().organizer(Supplier { User("an organizer") })
            context().limitDateToConfirm(Supplier { nextWeek })
            context().partyDate(Supplier { nextWeek })

            describe("when the party has no guests") {
                describe("when requires total cost of party") {
                    it("returns zero") {
                        assertThat(context().party().totalCost()).isEqualTo(0.0)
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
                    beforeEach { context().guest().confirmAssistanceTo(context().party()) }
                    it("Returns the exact number of confirmed guests") {
                        assertThat(context().party().numberOfConfirmedGuests()).isEqualTo(1)
                    }
                }
            }

            describe("whith one comodity") {
                context().commodity(Supplier { Commodity(context().product(), 1.0) })
                context().product(Supplier { Product("Cocacola", Presentation(2.5, PresentationPack.LITRE), 85.00) })

                beforeEach {
                    context().party().addCommodity(context().commodity())
                }
                it("Returns spending total zero") {
                    assertThat(context().party().totalCost()).isEqualTo(0.0)
                }

                describe("with a guest confirmed the invitation") {
                    context().guest(Supplier { User("moncho") })

                    beforeEach {
                        context().party().invite(context().guest())
                        context().guest().confirmAssistanceTo(context().party())
                    }
                    it("Returns 85 as total cost of party") {
                        assertThat(context().party().totalCost()).isEqualTo(85.0)
                    }
                }
            }

            describe("with two commodities") {
                context().product(Supplier { Product("Cocacola", Presentation(2.5, PresentationPack.LITRE), 85.00) })
                context().otherProduct(Supplier { Product("Pan", Presentation(0.5, PresentationPack.KILO), 50.00) })
                context().commodity(Supplier { Commodity(context().product(), 1.0) })
                context().otherCommodity(Supplier { Commodity(context().otherProduct(), 0.25) })

                beforeEach {
                    context().party().addCommodity(context().commodity())
                    context().party().addCommodity(context().otherCommodity())
                }
                describe("and two confirmed Guest") {
                    context().guest(Supplier { User("moncho") })
                    context().otherGuest(Supplier { User("Dante") })

                    beforeEach {
                        context().party().invite(context().guest())
                        context().party().invite(context().otherGuest())
                        context().guest().confirmAssistanceTo(context().party())
                        context().otherGuest().confirmAssistanceTo(context().party())
                    }

                    it("Returns 195 as total cost of party") {
                        assertThat(context().party().totalCost()).isEqualTo(195.0)
                    }
                }

            }

            describe("With expired confirmation date") {
                context().limitDateToConfirm(Supplier { weekAgo })
                context().partyDate(Supplier { nextWeek })

                describe("when guest want confirm assistance") {
                    context().guest(Supplier { User("moncho") })
                    context().otherGuest(Supplier { User("Dante") })

                    beforeEach {
                        context().party().invite(context().guest())
                        context().party().invite(context().otherGuest())
                    }

                    itThrows(CannotConfirmAssitanceException::class.java, "cannot confirm assitance",
                            { context().guest().confirmAssistanceTo(context().party()) },
                            { e -> assertThat(e).hasMessage("The confirmation date expired") }
                    )
                }
            }
        }
    }
}
