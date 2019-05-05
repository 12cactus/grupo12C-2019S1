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
        describe("dado un evento PoolMoney"){
            context().baquita( Supplier{ PoolMoney.crear(mutableListOf<Guest>(), mañana) })

            describe("con 100 de spendings"){
                context().gasto(Supplier { Spending.crear(100, "bebidas") })

                describe("con 2 guests"){
                    context().invitado(Supplier { Guest.crear("Moncho") })
                    context().otroInvitado(Supplier { Guest.crear("Rodolfo") })
                    describe("cuando pedimos el spending"){
                        it("obtenemos el valor 50"){
                            context().baquita().cargar( context().gasto() )
                            context().baquita().invitar(context().invitado())
                            context().baquita().invitar(context().otroInvitado())
                            context().invitado().asistirA(context().baquita())
                            context().otroInvitado().asistirA(context().baquita())

                            assertThat( context().baquita().gastoPorConfirmado()).isEqualTo(50)
                        }
                    }
                }
            }
            describe("sin spendings"){
                describe("con 2 guests") {
                    context().invitado(Supplier { Guest.crear("Moncho") })
                    context().otroInvitado(Supplier { Guest.crear("Rodolfo") })

                    describe("cada guest agrega un spending distinto"){
                        context().gasto(Supplier { Spending.crear(100, "bebidas") })
                        context().otroGasto(Supplier { Spending.crear(200, "tutuca") })

                        it("cuando pedimos los spendings obtenemos el valor 150"){
                            context().baquita().invitar(context().invitado())
                            context().baquita().invitar(context().otroInvitado())
                            context().invitado().asistirA(context().baquita())
                            context().otroInvitado().asistirA(context().baquita())
                            context().invitado().agregarGasto(context().gasto(), context().baquita())
                            context().otroInvitado().agregarGasto(context().otroGasto(), context().baquita())

                            assertThat(context().baquita().gastoPorConfirmado()).isEqualTo(150)
                        }

                        it("cuando pedimos el balance de cada guest obtenemos los valores 50 y -50"){
                            context().baquita().invitar(context().invitado())
                            context().baquita().invitar(context().otroInvitado())
                            context().invitado().asistirA(context().baquita())
                            context().otroInvitado().asistirA(context().baquita())
                            context().invitado().agregarGasto(context().gasto(), context().baquita())
                            context().otroInvitado().agregarGasto(context().otroGasto(), context().baquita())

                           // assertThat(context().baquita().balanceDe(context().guest())).isEqualTo(-50)
                            assertThat(context().baquita().balanceDe(context().otroInvitado())).isEqualTo(50)
                        }
                    }
                }
            }





        }
    }

}
