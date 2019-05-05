package edu.unq.desapp.eventeando.User.organizer

import edu.unq.desapp.eventeando.User.User

/**
 * A subclass of user
 */
class Organizer: User() {
    /**
     * @return false
     */
    override fun isAGuest(): Boolean = false
    /**
     * @return true
     */
    override fun isAOrganizer(): Boolean  = true

}
