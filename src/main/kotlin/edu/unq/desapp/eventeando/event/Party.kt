package edu.unq.desapp.eventeando.event

import java.time.LocalDate

/**
 * In this type of events the expenses of the same are not distributed but
 * they are paid by the organizer. When creating the event, the organizer
 * does not just enter a list of users to invite but also how long before
 * they are allowed confirmations
 */
class Party(val organizer: String, val confirmationAllowedDate: LocalDate, date: LocalDate) : Event(date)
    //Por el momento el organizer es un String pero esto tiene que cambiar para que sea una persona o usuario del sist
