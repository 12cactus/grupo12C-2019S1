package edu.unq.desapp.eventeando.event

import ar.com.dgarcia.javaspec.api.JavaSpec
import ar.com.dgarcia.javaspec.api.JavaSpecRunner
import edu.unq.desapp.eventeando.element.Element
import edu.unq.desapp.eventeando.template.Template
import org.junit.runner.RunWith
import java.util.function.Supplier
import org.assertj.core.api.Assertions.assertThat

/**
 * Test a template of event
 */
@RunWith(JavaSpecRunner::class)
class TemplateTest: JavaSpec<TemplateContextTest>() {
    override fun define() {
        describe("dado un template vacio"){
            context().template(Supplier { Template.crear("asadito","e'pa lo'pibe") })

                it("se le pide description y retorna: e'pa lo'pibe"){
                    assertThat(context().template().description()).isEqualTo("e'pa lo'pibe")
                }
            describe("se le agregan dos elementos"){
                context().element(Supplier { Element("papas fritas",5.50) })
                context().otherElement(Supplier { Element("mani pelado",7.00) })

                it("se le pide el costo por persona el cost es 12.50"){
                    context().template().add(context().element())
                    context().template().add(context().otherElement())

                    assertThat(context().template().costPerPerson()).isEqualTo(12.50)
                }

            }

        }
    }

}
