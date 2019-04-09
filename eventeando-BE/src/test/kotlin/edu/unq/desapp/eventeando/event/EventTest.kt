package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.util.function.Supplier

@RunWith(JavaSpecRunner::class)
class EventTest: JavaSpec<EventContextTest>() {

    override fun define()
    {
        describe("Given a event") {
            context().event(Supplier { Event.create("Fiesta sorpresa") })

            it("returns his name") {
                assertThat(context().event().name()).isEqualTo("Fiesta sorpresa")
            }
        }

    }
}
