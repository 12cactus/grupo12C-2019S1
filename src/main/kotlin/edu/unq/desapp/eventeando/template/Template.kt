package edu.unq.desapp.eventeando.template

import edu.unq.desapp.eventeando.element.Element

/**
 *
 */
class Template {
    private var description: String = ""
    private var title: String = ""
    private var elements:  MutableList<Element> = mutableListOf()

    companion object {
        fun crear(title:String, description: String): Template {
            val template = Template()
            template.title = title
            template.description = description
            return template
        }
    }

    fun add(element: Element){
        elements.add(element)
    }

    fun costPerPerson():Double{
        return elements.fold(0.00){ total, element -> element.proportionPerPerson() + total}
    }

    fun description(): String = description
}
