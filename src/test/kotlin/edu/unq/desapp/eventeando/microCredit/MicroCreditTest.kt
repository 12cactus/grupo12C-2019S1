package edu.unq.desapp.eventeando.microCredit

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.guest.GuestContextTest
import org.junit.runner.RunWith
import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDate
import java.util.function.Supplier


@RunWith(JavaSpecRunner::class)
class MicroCreditTest : JavaSpec<GuestContextTest>(){

    override fun define() {
        describe("Dado un MicroCredit"){

        }
    }
}