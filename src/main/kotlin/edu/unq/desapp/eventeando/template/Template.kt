package edu.unq.desapp.eventeando.template

import edu.unq.desapp.eventeando.elemento.Elemento

class Template {
    var elementos:  MutableList<Elemento> = mutableListOf<Elemento>()

    companion object {
        fun crear(): Template {
            val template = Template()
            return template
        }
    }

    fun agregar(elemento: Elemento){
        elementos.add(elemento)
    }

    fun costoPorPersona():Double{
        return elementos.fold(0.00){total, elemento -> elemento.proporcionPorPersona() + total}
    }
}