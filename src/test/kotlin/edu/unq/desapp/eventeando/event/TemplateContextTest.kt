package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.element.Element
import edu.unq.desapp.eventeando.template.Template
import java.util.function.Supplier

interface TemplateContextTest: TestContext {

    fun template(): Template
    fun template(supplier: Supplier<Template>)

    fun elemento(): Element
    fun elemento(supplier: Supplier<Element>)

    fun otroElemento(): Element
    fun otroElemento(supplier: Supplier<Element>)

}
