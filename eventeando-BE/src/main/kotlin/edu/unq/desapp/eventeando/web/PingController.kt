package edu.unq.desapp.eventeando.web

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {

    @CrossOrigin(origins = arrayOf("http://localhost:4200"))
    @GetMapping("/ping")
    fun ping() = """{"name" : "pong"}"""

}
