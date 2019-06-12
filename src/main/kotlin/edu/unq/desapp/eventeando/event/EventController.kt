package edu.unq.desapp.eventeando.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/event")
class EventController {

    @Autowired
    lateinit var eventCreator: EventCreator

    @CrossOrigin(origins = arrayOf("http://localhost:4200"))
    @PostMapping
    fun getProfileForLogin(@RequestBody @Valid creationEventInfo: CreationEventInfo): EventTO{
        return EventTO("id")
    }
}

data class EventTO(val id: String) {

}

data class CreationEventInfo(val guests: List<UserTO>, val eventType: EventType)

data class UserTO(val id: String)
/*

@RestController
@RequestMapping("/catalog")
class CatalogController {

    @Autowired
    lateinit var gameCatalog: Catalog

    @GetMapping
    fun getCatalog() = gameCatalog

    @GetMapping("/categories")
    fun getCategories(): List<CategoryIdentifier> {
        return gameCatalog.lists.map { CategoryIdentifier(it.id) }
    }

    @GetMapping("/search")
    fun search(@RequestParam textPortion: String): GameList {
        val space = " "
        val keywords = textPortion.split(space)
        val matchingGames = gameCatalog.gamesContainingKeywordsInTitle(keywords)
        val gameIds = matchingGames.map { game -> ListElement(game.id) }
        return GameList("Resultados", RowOrientation.PORTRAIT, gameIds, "123")
    }

    private fun Catalog.gamesContainingKeywordsInTitle(keywords: List<String>): List<Game> {
        return games.values
                .filter { it.containsAllKeywordsInTitle(keywords) }
    }

    private fun Game.containsAllKeywordsInTitle(keywords: List<String>) = keywords.all { title.contains(it) }

}

data class CategoryIdentifier(val id: String)
*/
