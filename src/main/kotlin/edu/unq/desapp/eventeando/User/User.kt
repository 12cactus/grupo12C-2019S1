package edu.unq.desapp.eventeando.User

/**
 * Models an abstract user
 */
abstract class User() {
    lateinit var name: String
    lateinit var state: User

    /**
     * @return true if user is a Guest
     */
    abstract fun isAGuest(): Boolean

    /**
     * @return true if user is a Organizer
     */
    abstract fun isAOrganizer(): Boolean
}
