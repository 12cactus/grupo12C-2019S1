package edu.unq.desapp.eventeando.movements

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.guest.Guest
import org.junit.runner.RunWith
import org.assertj.core.api.Assertions.assertThat
import java.util.function.Supplier

/**
 * Tests a movement
 */
@RunWith(JavaSpecRunner::class)
class MovementTest: JavaSpec<MovementContextTest>() {
    override fun define(){
        describe("Dado un guest sin movimientos"){
            context().guest(Supplier { Guest.crear("Jhon Snow") })

            describe("El inviado realiza un bankDeposit de 100"){
                it("El guest tiene un Movement del type BANKDEPOSIT de 100 en su cuenta"){
                    context().guest().putDown(100.00)
                    assertThat(context().guest().getBankDeposits().size).isEqualTo(1)
                    assertThat(context().guest().getBankDeposits().last().cost()).isEqualTo(100.00)
                }

            }
            describe("El inviado realiza un bankWithdrawal de 100"){
                it("El guest tiene un Movement del type BANKWITHDRAWAL de 100 en su cuenta"){
                    context().guest().retirar(100.00)
                    assertThat(context().guest().getBankWithdrawals().size).isEqualTo(1)
                    assertThat(context().guest().getBankWithdrawals().last().cost()).isEqualTo(100.00)
                }
            }

            describe("El guest realiza un bankDeposit de 500 y un bankWithdrawal de 150"){
                it("El guest tiene 2 movimientos y el resumen da un cost de 350"){
                    context().guest().putDown(500.00)
                    context().guest().retirar(150.00)
                    assertThat(context().guest().getMovements().size).isEqualTo(2)
                    assertThat(context().guest().getSummary()).isEqualTo(350.00)
                }
            }

        }
    }
}
