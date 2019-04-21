package edu.unq.desapp.eventeando.eventos

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.elemento.Elemento
import edu.unq.desapp.eventeando.template.Template
import org.junit.runner.RunWith
import java.util.function.Supplier
import org.assertj.core.api.Assertions.assertThat

@RunWith(JavaSpecRunner::class)
class TemplateTest: JavaSpec<TemplateContextTest>() {
    override fun define() {
        describe("dado un template vacio"){
            context().template(Supplier { Template.crear() })
            describe("se le agregan dos elementos"){
                context().elemento(Supplier { Elemento.crear("papas fritas",5.50) })
                context().otroElemento(Supplier { Elemento.crear("mani pelado",7.00) })

                it("se le pide el costo por persona el valor es 12.50"){
                    context().template().agregar(context().elemento())
                    context().template().agregar(context().otroElemento())

                    assertThat(context().template().costoPorPersona()).isEqualTo(12.50)
                }

            }

        }
    }

}