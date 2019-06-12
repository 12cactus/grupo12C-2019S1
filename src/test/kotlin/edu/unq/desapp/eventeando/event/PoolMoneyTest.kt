package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import ar.com.dgarcia.kotlinspec.api.KotlinSpec
import edu.unq.desapp.eventeando.element.EventExpense
import edu.unq.desapp.eventeando.element.GeneralExpense
import edu.unq.desapp.eventeando.guest.User
import org.assertj.core.api.Assertions
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.function.Supplier

/**
 * Test a poolMoney event
 */
@RunWith(JavaSpecRunner::class)
class PoolMoneyTest : KotlinSpec() {

    private val today = LocalDate.now()
    private val nextWeek = today.plusDays(7)

    override fun define() {
        describe("Given a poolMoney") {
            val organizers by let { mutableListOf(User("an organizer")) }
            val poolMoneyDate by let {nextWeek}
            val limitDateToConfirm by let{nextWeek}
            val manualConfirmedGuests by let<Int>()
            val eventExpenseCalculator by let<EventExpenseCalculator?>()
            val guests by let{ mutableListOf<User>()}
            val eventExpenses by let<MutableList<EventExpense>>()
            val poolMoney by let<PoolMoney>()

            poolMoney {
                PoolMoney(organizers(),
                        poolMoneyDate(),
                        limitDateToConfirm(),
                        manualConfirmedGuests(),
                        eventExpenseCalculator(),
                        guests(),
                        eventExpenses())
            }

            describe("when the pool money is a buy first mode") {
                eventExpenseCalculator{null}

                describe("#costPerConfirmedGuest") {
                    describe("with an event expense that represents the total cost of what was spent") {
                        val expense by let { GeneralExpense("Gasto total", 1750.90) }
                        eventExpenses { mutableListOf(expense()) }

                        describe("with some manual confirmed guests") {
                            manualConfirmedGuests{10}

                            it("Returns the exact cost per confirmed Guest") {
                                Assertions.assertThat(poolMoney().costPerPerson()).isEqualTo(176.0)
                            }
                        }

                        describe("with no manually confirmed guest") {
                            manualConfirmedGuests {0}

                            itThrows(DivideByZeroException::class.java, "throws an error because ManualConfirmedGuests must be higher than zero",
                                    { poolMoney().costPerPerson() },
                                    { e -> Assertions.assertThat(e).hasMessage("Cannot calculate. ManualConfirmedGuests must be higher than zero") }
                            )
                        }
                    }

                }
            }
        }
    }
}
