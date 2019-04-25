package edu.unq.desapp.eventeando.template

import edu.unq.desapp.eventeando.elemento.Elemento

class Template {
    private var descripcion: String = ""
    private var titulo: String = ""
    private var elementos:  MutableList<Elemento> = mutableListOf<Elemento>()

    companion object {
        fun crear(titulo:String, descripcion: String): Template {
            val template = Template()
            template.titulo = descripcion
            template.descripcion = descripcion
            return template
        }
    }

    fun agregar(elemento: Elemento){
        elementos.add(elemento)
    }

    fun costoPorPersona():Double{
        return elementos.fold(0.00){total, elemento -> elemento.proporcionPorPersona() + total}
    }

    fun descripcion(): String = descripcion
}