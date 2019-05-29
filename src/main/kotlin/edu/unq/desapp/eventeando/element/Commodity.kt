package edu.unq.desapp.eventeando.element

class Commodity(val product: Product, val neededPerPerson: Double ) {
    fun totalFromNeededPerPerson(numberOfConfirmedGuests: Int): Double {
        return numberOfConfirmedGuests * neededPerPerson * product.price
    }

}
