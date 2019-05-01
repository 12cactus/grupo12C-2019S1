package edu.unq.desapp.eventeando.eventos

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import edu.unq.desapp.eventeando.elemento.Elemento
import edu.unq.desapp.eventeando.template.Template
import java.util.function.Supplier

interface TemplateContextTest: TestContext {

    fun template(): Template
    fun template(supplier: Supplier<Template>)

    fun elemento(): Elemento
    fun elemento(supplier: Supplier<Elemento>)

    fun otroElemento(): Elemento
    fun otroElemento(supplier: Supplier<Elemento>)

}