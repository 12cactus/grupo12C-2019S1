package edu.unq.desapp.eventeando.user

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.User.organizer.Organizer
import edu.unq.desapp.eventeando.guest.Guest
import org.assertj.core.api.Assertions
import org.junit.runner.RunWith
import java.util.function.Supplier

/**
 * Tests a User
 */
@RunWith(JavaSpecRunner::class)
class UserTest: JavaSpec<UserContextTest>() {
    override fun define()
    {

        describe("give a Guest to set the User") {
            context().guest(Supplier { Guest() })
            context().userGuest(Supplier { context().guest() })

            describe("when we ask the user if he is a guest ") {
                it("get True ") {
                    Assertions.assertThat(context().userGuest().isAGuest()).isTrue()
                }
            }
            describe("when we ask the user if he is a organizer ") {
                it("get False ") {
                    Assertions.assertThat(context().userGuest().isAOrganizer()).isFalse()
                }
            }
        }
        describe("give a Guest to set the User"){
            context().organizer(Supplier { Organizer() })
            context().userOrganizer(Supplier { context().organizer() })
            describe("when we ask the user if he is a guest "){
                it("get False ") {
                    Assertions.assertThat(context().userOrganizer().isAGuest()).isFalse()
                }
            }
            describe("when we ask the user if he is a organizer "){
                it("get True ") {
                    Assertions.assertThat(context().userOrganizer().isAOrganizer()).isTrue()
                }
            }
        }
    }
}