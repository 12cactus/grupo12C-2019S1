package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.element.Commodity
import edu.unq.desapp.eventeando.guest.User
import java.time.LocalDate

/**
 * In this type of events the expenses of the same are not distributed but
 * they are paid by the organizer. When creating the event, the organizer
 * does not just enter a list of guests to invite but also how long before
 * they are allowed confirmations
 */
class Party(val organizer: User,
            date: LocalDate,
            confirmationAllowedDate: LocalDate,
            private val shoppingList: MutableList<Commodity> = mutableListOf()) :
        Event(date, confirmationAllowedDate) {

    /**
     *
     */
    override fun totalCost(): Double {
        return shoppingList
                .fold(0.00)
                { total, commoodity ->
                    total + commoodity.totalFromNeededPerPerson(numberOfConfirmedGuests())
                }
    }

    /**
     *
     */
    override fun invite(user: User) {
        this.guests.add(user)
        user.invitationFrom(this)
    }

    /**
     *
     */
    fun addCommodity(commodity: Commodity) {
        shoppingList.add(commodity)
    }
}
