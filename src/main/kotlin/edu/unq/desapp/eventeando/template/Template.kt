package edu.unq.desapp.eventeando.template

import edu.unq.desapp.eventeando.element.Element

class Template {
    private var descripcion: String = ""
    private var titulo: String = ""
    private var elements:  MutableList<Element> = mutableListOf<Element>()

    companion object {
        fun crear(titulo:String, descripcion: String): Template {
            val template = Template()
            template.titulo = descripcion
            template.descripcion = descripcion
            return template
        }
    }

    fun agregar(element: Element){
        elements.add(element)
    }

    fun costoPorPersona():Double{
        return elements.fold(0.00){ total, elemento -> elemento.proporcionPorPersona() + total}
    }

    fun descripcion(): String = descripcion
}
