package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.element.EventExpenseConfiguration
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
                Party(context().organizers(),
                        context().partyDate(),
                        context().limitDateToConfirm(),
                        context().eventExpenseCalculator(),
                        context().guests())
            })
            context().organizer(Supplier { User("an organizer") })
            context().organizers(Supplier { mutableListOf(context().organizer()) })
            context().limitDateToConfirm(Supplier { nextWeek })
            context().partyDate(Supplier { nextWeek })

            describe("when eventExpenseCalculator configuration is empty") {
                context().guests(Supplier { mutableListOf<User>() })
                context().eventExpenseCalculator(Supplier { EventExpenseCalculator.create(mutableListOf()) })

                describe("and request event expenses to the party") {
                    itThrows(MissingEventExpenseConfigurationException::class.java, "throws an error because dont have configurations",
                            { context().party().totalCost() },
                            { e -> assertThat(e).hasMessage("Cannot calculate. Event expense configuration is empty") }
                    )
                }
            }

            describe("when eventExpenseCalculator has configuration ") {
                context().product(Supplier { Product("Cocacola", Presentation(2.5, PresentationPack.LITRE), 80.00) })
                context().otherProduct(Supplier { Product("Pan", Presentation(0.5, PresentationPack.KILO), 60.00) })
                context().eventExpenseConfiguration(Supplier { EventExpenseConfiguration(context().product(), 0.5) })
                context().otherEventExpenseConfiguration(Supplier { EventExpenseConfiguration(context().otherProduct(), 0.25) })
                context().eventExpenseConfigurations(Supplier {
                    mutableListOf(context().eventExpenseConfiguration(), context().otherEventExpenseConfiguration())
                })
                context().eventExpenseCalculator(Supplier { EventExpenseCalculator(context().eventExpenseConfigurations()) })

                describe("and the party has no guests") {
                    context().guests(Supplier { mutableListOf<User>() })
                    describe("when requires total cost of party") {
                        it("returns zero") {
                            assertThat(context().party().totalCost()).isEqualTo(0.0)
                        }
                    }
                }

                describe("when invite some guests") {
                    context().guest(Supplier { User("invitado1") })
                    context().otherGuest(Supplier { User("invitado2") })
                    context().guests(Supplier { mutableListOf(context().guest(), context().otherGuest()) })

                    it("Returns the exact number of guests") {
                        assertThat(context().party().numberOfGuests()).isEqualTo(2)
                    }

                    describe("and expired confirmation date") {
                        context().limitDateToConfirm(Supplier { weekAgo })
                        context().partyDate(Supplier { nextWeek })

                        describe("when guest want confirm assistance") {
                            itThrows(CannotConfirmAssitanceException::class.java, "cannot confirm assitance",
                                    { context().guest().confirmAssistanceTo(context().party()) },
                                    { e -> assertThat(e).hasMessage("The confirmation date expired") }
                            )
                        }
                    }

                    describe("and a guest confirm assistance") {
                        beforeEach { context().guest().confirmAssistanceTo(context().party()) }
                        it("Returns the exact number of confirmed guests") {
                            assertThat(context().party().numberOfConfirmedGuests()).isEqualTo(1)
                        }

                        describe("when requires total cost of party") {
                            it("returns the total for the confirmed guests") {
                                assertThat(context().party().totalCost()).isEqualTo(140.0)
                            }
                        }
                    }
                }
            }
        }
    }
}
