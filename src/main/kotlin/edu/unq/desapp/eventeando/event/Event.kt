package edu.unq.desapp.eventeando.event

class Event{
    var name: String? = null

    companion object {
        fun create(aName: String): Event {
            val event = Event()
            event.name = aName
            return event
        }
    }

    fun name(): String? = this.name
}
