package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.element.Element
import edu.unq.desapp.eventeando.template.Template
import java.util.function.Supplier

interface TemplateContextTest: TestContext {

    fun template(): Template
    fun template(supplier: Supplier<Template>)

    fun element(): Element
    fun element(supplier: Supplier<Element>)

    fun otherElement(): Element
    fun otherElement(supplier: Supplier<Element>)

}
