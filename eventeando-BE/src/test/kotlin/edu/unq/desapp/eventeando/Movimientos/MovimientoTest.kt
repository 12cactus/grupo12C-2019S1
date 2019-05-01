package edu.unq.desapp.eventeando.Movimientos

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.cuentaCorriente.Movimiento
import edu.unq.desapp.eventeando.cuentaCorriente.TipoDeMovimiento
import edu.unq.desapp.eventeando.eventos.Canasta
import edu.unq.desapp.eventeando.invitado.Invitado
import org.junit.runner.RunWith
import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDate
import java.util.function.Supplier

@RunWith(JavaSpecRunner::class)
class MovimientoTest: JavaSpec<MovimientoContextTest>() {
    override fun define(){
        describe("Dado un invitado sin movimientos"){
            context().invitado(Supplier { Invitado.crear("Jhon Snow") })

            describe("El inviado realiza un deposito de 100"){
                it("El invitado tiene un Movimiento del tipo DEPOSITO de 100 en su cuenta"){
                    context().invitado().depositar(100.00)
                    assertThat(context().invitado().getDepositos().size).isEqualTo(1)
                    assertThat(context().invitado().getDepositos().last().monto()).isEqualTo(100.00)
                }

            }
            describe("El inviado realiza un retiro de 100"){
                it("El invitado tiene un Movimiento del tipo RETIRO de 100 en su cuenta"){
                    context().invitado().retirar(100.00)
                    assertThat(context().invitado().getRetiros().size).isEqualTo(1)
                    assertThat(context().invitado().getRetiros().last().monto()).isEqualTo(100.00)
                }
            }

            describe("El invitado realiza un deposito de 500 y un retiro de 150"){
                it("El invitado tiene 2 movimientos y el resumen da un valor de 350"){
                    context().invitado().depositar(500.00)
                    context().invitado().retirar(150.00)
                    assertThat(context().invitado().getMovimientos().size).isEqualTo(2)
                    assertThat(context().invitado().getResumen()).isEqualTo(350.00)
                }
            }

        }
    }
}