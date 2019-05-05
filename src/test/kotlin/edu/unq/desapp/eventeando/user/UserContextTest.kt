package edu.unq.desapp.eventeando.user

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.User.organizer.Organizer
import edu.unq.desapp.eventeando.guest.Guest
import java.util.function.Supplier

interface UserContextTest : TestContext {

    fun userGuest(): Guest
    fun userGuest(supplier: Supplier<Guest>)

    fun guest(): Guest
    fun guest(supplier: Supplier<Guest>)

    fun userOrganizer(): Organizer
    fun userOrganizer(supplier: Supplier<Organizer>)

    fun organizer(): Organizer
    fun organizer(supplier: Supplier<Organizer>)
}