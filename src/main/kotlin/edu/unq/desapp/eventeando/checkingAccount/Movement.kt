package edu.unq.desapp.eventeando.checkingAccount

import java.time.LocalDate

/**
 * Represents a bank movement
 */
class Movement(val date: LocalDate, val cost: Double, val type: MovementType)
