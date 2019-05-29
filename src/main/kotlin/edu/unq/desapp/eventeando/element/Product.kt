package edu.unq.desapp.eventeando.element

class Product(val name: String, val presentation: Presentation, val price: Double)

class Presentation(val quatity: Double, val presentationPack: PesentationPack)
//Por ahora el peso sólo, esto debe evolucionar para tener una forma de que
//por cada kilo, litro o lo que sea, sepamos cuanto equivale para una persona.
//Algo asi como que se calcula medio kilo de asado por persona..


enum class PesentationPack(){
    KILO,
    LITRE,
    UNITY
}