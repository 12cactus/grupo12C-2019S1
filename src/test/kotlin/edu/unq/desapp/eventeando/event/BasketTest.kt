//package edu.unq.desapp.eventeando.event
//
//import ar.com.dgarcia.javaspec.api.JavaSpec
//import ar.com.dgarcia.javaspec.api.JavaSpecRunner
//import edu.unq.desapp.eventeando.spending.Spending
//import edu.unq.desapp.eventeando.guest.User
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.Ignore
//import org.junit.runner.RunWith
//import java.time.LocalDate
//import java.util.function.Supplier
//
///**
// * Test a basket event
// */
//@Ignore
//@RunWith(JavaSpecRunner::class)
//class BasketTest: JavaSpec<BasketContextTest>() {
//    val hoy = LocalDate.now()
//    val mañana = hoy.plusDays(1)
//
//    override fun define()
//    {
//        describe("given an basket event") {
//            context().basket(Supplier { Basket(mañana) })
//
//            describe("without spendings"){
//                describe("when send spends()"){
//                    it("is empty") {
//                        assertThat(context().basket().spendings).isEmpty()
//                    }
//                }
//
//                describe("when send totalCost()"){
//                    it("get neutral cost") {
//                        assertThat(context().basket().totalCost()).isEqualTo(0)
//                    }
//                }
//            }
//
//            describe("with spendings"){
//                context().basket(Supplier { Basket(mañana) })
//
//                describe("when send spends()"){
//                    it("get an amount of spends") {
//                        addSpendsToBasket()
//                        assertThat(context().basket().spendings.count()).isEqualTo(2)
//                    }
//                }
//
//                describe("when send totalCost()"){
//                    it("get the sum of costs") {
//                        addSpendsToBasket()
//                        assertThat(context().basket().totalCost()).isEqualTo(10)
//                    }
//                }
//            }
//
//            describe("without guests"){
//                describe("when send guests()"){
//                    it("obtenemos una nula de guests") {
//                        assertThat(context().basket().users.count()).isEqualTo(0)
//                    }
//                }
//            }
//
//            describe("with unconfirmed guests"){
//                context().spendings(Supplier { mutableListOf<Spending>() })
//                context().guest(Supplier { User("Carlos") })
//                context().basket().invite(context().guest())
//                context().basket().invite(User("jose"))
//
//                describe("when send guests()"){
//                    it("gets an amount of unconfirmed guests") {
//                        assertThat(context().basket().users.count()).isEqualTo(2)
//                    }
//                }
//
//                it("gets empty confirmed lists") {
//                    assertThat(context().basket().confirmedGuests().count()).isEqualTo(0)
//                }
//
//                describe("when a user confirm asistence to an event"){
//                    it("get an confirmed guests list with the user") {
//                        context().guest().attendTo(context().basket())
//                        assertThat(context().basket().confirmedGuests().size).isEqualTo(1)
//                    }
//                }
//            }
//        }
//    }
//
//    fun addSpendsToBasket(){
//        val spendsForBasket = listOf(Spending(5, "frites"), Spending(5, "water"))
//        spendsForBasket.forEach { spend -> context().basket().load(spend) }
//    }
//}
//
