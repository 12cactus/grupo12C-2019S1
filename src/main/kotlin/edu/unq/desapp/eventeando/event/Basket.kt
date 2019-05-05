package edu.unq.desapp.eventeando.event

import edu.unq.desapp.eventeando.spending.Spending
import edu.unq.desapp.eventeando.guest.Guest
import java.time.LocalDate

/**
 * Models a putlock
 */
class Basket : Event(){

    companion object {
        fun crear(spendings: MutableList<Spending>, guests: MutableList<Guest>, dateConfirmation: LocalDate): Basket {
            val basket = Basket()
            basket.spendings = spendings
            basket.guests = guests
            basket.confirmationDate = dateConfirmation
            return basket
        }
    }
}
