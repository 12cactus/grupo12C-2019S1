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
    private val weekAgo = today.minusDays(7)
    private val nextWeek = today.plusDays(7)

    override fun define() {
        describe("Given a poolMoney") {
            val organizers by let { mutableListOf(User("an organizer")) }
            val poolMoneyDate by let<LocalDate>()
            val limitDateToConfirm by let<LocalDate>()
            val manualConfirmedGuests by let<Int>()
            val eventExpenseCalculator by let<EventExpenseCalculator?> { null }
            val guests by let<MutableList<User>>()
            val eventExpenses by let<MutableList<EventExpense>>()
            val poolMoney by let<PoolMoney>()

            poolMoney {
                PoolMoney(organizers,
                        poolMoneyDate(),
                        limitDateToConfirm(),
                        manualConfirmedGuests(),
                        eventExpenseCalculator(),
                        guests(),
                        eventExpenses())
            }

            context().poolMoney(Supplier {
                PoolMoney(context().organizers(),
                        context().poolMoneyDate(),
                        context().limitDateToConfirm(),
                        manualConfirmedGuests,
                        context().eventExpenseCalculator(),
                        context().guests(),
                        context().eventExpenses())
            })

            describe("when the pool money is a buy first mode") {
                context().eventExpenseCalculator(Supplier { null })

                describe("#costPerConfirmedGuest") {
                    describe("with an event expense that represents the total cost of what was spent") {
                        context().expense(Supplier { GeneralExpense("Gasto total", 1750.90) })
                        context().eventExpenses(Supplier { mutableListOf(context().expense()) })

                        describe("with some manual confirmed guests") {
                            manualConfirmedGuests = 10

                            it("Returns the exact cost per confirmed Guest") {
                                Assertions.assertThat(context().poolMoney().costPerPerson()).isEqualTo(176.0)
                            }
                        }

                        describe("with no manually confirmed guest") {
                            manualConfirmedGuests = 0

                            itThrows(DivideByZeroException::class.java, "throws an error because ManualConfirmedGuests must be higher than zero",
                                    { context().poolMoney().costPerPerson() },
                                    { e -> Assertions.assertThat(e).hasMessage("Cannot calculate. ManualConfirmedGuests must be higher than zero") }
                            )
                        }
                    }

                }
            }
        }
    }
}
