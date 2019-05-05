package edu.unq.desapp.eventeando.event


import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.runner.RunWith
import java.util.function.Supplier

/**
 * Test a party event
 */
@Ignore
@RunWith(JavaSpecRunner::class)
class PartyTest: JavaSpec<PartyContextTest>() {

    val today = LocalDate.now()
    val yesterday = today.minusDays (7)
    val tomorrow = today.plusDays(1)

    override fun define() {
        describe("Give an event party") {
            context().party( Supplier { Party("organizador", today, tomorrow ) })

            describe("whitout guests"){
                context().guests(Supplier { mutableListOf<Guest>() })
                
                describe("when we ask about the spendings") {
                    it("we get neutral cost") {
                        assertThat(context().party().totalCost()).isEqualTo(0)
                    }
                }

                describe("when we invite an aGuest"){
                    context().guest(Supplier { Guest() })
                    it("get the amount of guests equals 1"){
                        context().party().invite(context().guest())
                        assertThat(context().party().guests.size).isEqualTo(1)
                    }
                }

                describe("without spendings"){
                    context().spendings(Supplier { mutableListOf<Spending>() })

                    describe("when we ask about spendings"){
                        it("get the neutral cost"){
                            assertThat(context().party().totalCost()).isEqualTo(0)
                        }
                    }
                }

                describe("with a spending") {
                    context().spending(Supplier { Spending(100, "sarasa") })

                    describe("with guest without confirmation") {
                        context().guest(Supplier { Guest() })
                        context().guests(Supplier { mutableListOf(context().guest()) })

                        describe("when get the spendings") {
                            it("obtenemos cost neutro") {
                                context().party().load(context().spending())
                                assertThat(context().party().totalCost()).isEqualTo(0)
                            }
                        }

                        describe("cuando el guest confirma asistencia a la party") {
                            describe("cuando pedimos los spendings") {
                                it("obtenemos el cost 100") {
                                    context().guest().attendTo(context().party())
                                    context().party().load(context().spending())
                                    assertThat(context().party().totalCost()).isEqualTo(100)
                                }
                            }
                        }
                    }
                }
            }
        }

        describe("Dado un event party con fecha caduca de confirmación") {
            context().party(Supplier { Party("organizador", yesterday, today) })

            describe("cuando el guest confirma asistencia pasada la fecha de confirmación") {
                context().guest(Supplier { Guest() })
                context().guests(Supplier { mutableListOf(context().guest()) })

                it("obtenemos el cost nulo de guests confirmados") {
                    context().guest().attendTo(context().party())
                    assertThat(context().party().confirmedGuests().size).isEqualTo(0)
                }
            }
        }
    }
}
