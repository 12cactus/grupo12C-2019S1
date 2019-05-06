package edu.unq.desapp.eventeando.element

class Product(val name: String, val cost: Double, val presentation: Presentation)

class Presentation(val weight: String)
//Por ahora el peso sólo, esto debe evolucionar para tener una forma de que
//por cada kilo, litro o lo que sea, sepamos cuanto equivale para una persona.
//Algo asi como que se calcula medio kilo de asado por persona..
