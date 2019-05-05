package edu.unq.desapp.eventeando.microCredit

import ar.com.dgarcia.javaspec.api.contexts.TestContext
import java.util.function.Supplier

interface MicroCreditContextTest: TestContext {
    fun microCredit(): MicroCreditTest
    fun microCredit(supplier: Supplier<MicroCreditTest>)
}