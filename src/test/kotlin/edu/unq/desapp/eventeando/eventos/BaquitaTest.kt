package edu.unq.desapp.eventeando.eventos
import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.util.function.Supplier


@RunWith(JavaSpecRunner::class)
class BaquitaTest: JavaSpec<BaquitaContextTest>() {
    var hoy = LocalDate.now()
    var ayer = hoy.minusDays (7)
    var mañana = hoy.plusDays(1)
    override fun define() {
        describe("dado un evento Baquita"){
            context().baquita( Supplier{ Baquita.crear(mutableListOf<Invitado>(), mañana) })

            describe("con 100 de gastos"){
                context().gasto(Supplier { Gasto.crear(100, "bebidas") })

                describe("con 2 invitados"){
                    context().invitado(Supplier { Invitado.crear("Moncho") })
                    context().otroInvitado(Supplier { Invitado.crear("Rodolfo") })
                    describe("cuando pedimos el gasto"){
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
            describe("sin gastos"){
                describe("con 2 invitados") {
                    context().invitado(Supplier { Invitado.crear("Moncho") })
                    context().otroInvitado(Supplier { Invitado.crear("Rodolfo") })

                    describe("cada invitado agrega un gasto distinto"){
                        context().gasto(Supplier { Gasto.crear(100, "bebidas") })
                        context().otroGasto(Supplier { Gasto.crear(200, "tutuca") })

                        it("cuando pedimos los gastos obtenemos el valor 150"){
                            context().baquita().invitar(context().invitado())
                            context().baquita().invitar(context().otroInvitado())
                            context().invitado().asistirA(context().baquita())
                            context().otroInvitado().asistirA(context().baquita())
                            context().invitado().agregarGasto(context().gasto(), context().baquita())
                            context().otroInvitado().agregarGasto(context().otroGasto(), context().baquita())

                            assertThat(context().baquita().gastoPorConfirmado()).isEqualTo(150)
                        }

                        it("cuando pedimos el balance de cada invitado obtenemos los valores 50 y -50"){
                            context().baquita().invitar(context().invitado())
                            context().baquita().invitar(context().otroInvitado())
                            context().invitado().asistirA(context().baquita())
                            context().otroInvitado().asistirA(context().baquita())
                            context().invitado().agregarGasto(context().gasto(), context().baquita())
                            context().otroInvitado().agregarGasto(context().otroGasto(), context().baquita())

                           // assertThat(context().baquita().balanceDe(context().invitado())).isEqualTo(-50)
                            assertThat(context().baquita().balanceDe(context().otroInvitado())).isEqualTo(50)
                        }
                    }
                }
            }





        }
    }

}