package de.pcsoft.demo.ai.aicalculator.controller

import java.math.BigDecimal
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests for the [Calculator].
 */
class CalculatorTest {

    private val calculator = Calculator()

    /**
     * Verifies that all basic arithmetic operations are calculated correctly.
     */
    @Test
    fun basicOperationsAreCalculated() {
        assertEquals(BigDecimal("3"), calculator.calculate("1+2"))
        assertEquals(BigDecimal("2"), calculator.calculate("5-3"))
        assertEquals(BigDecimal("12"), calculator.calculate("3*4"))
        assertEquals(BigDecimal("4"), calculator.calculate("8/2"))
    }

    /**
     * Verifies that operator precedence and parentheses are respected.
     */
    @Test
    fun precedenceAndParenthesesAreRespected() {
        assertEquals(BigDecimal("7"), calculator.calculate("1+2*3"))
        assertEquals(BigDecimal("9"), calculator.calculate("(1+2)*3"))
    }

    /**
     * Verifies that decimal numbers are calculated without the rounding errors of a floating point calculation.
     */
    @Test
    fun decimalNumbersAreCalculatedExactly() {
        assertEquals(BigDecimal("0.3"), calculator.calculate("0.1+0.2"))
    }

    /**
     * Verifies that numbers far beyond the range of a primitive number type are calculated correctly.
     */
    @Test
    fun arbitraryNumbersAreCalculated() {
        assertEquals(
            BigDecimal("9999999999999999999999999999999999999999800000000000000000000000000000000000000000"),
            calculator.calculate("99999999999999999999999999999999999999999*99999999999999999999999999999999999999999-1")
        )
    }

    /**
     * Verifies that a leading sign and a sign combination are evaluated as sign of the following term.
     */
    @Test
    fun signsAreEvaluated() {
        assertEquals(BigDecimal("-3"), calculator.calculate("-1-2"))
        assertEquals(BigDecimal("1"), calculator.calculate("2+-1"))
    }

    /**
     * Verifies that an incomplete formula is made valid by the tolerance rule before it is evaluated.
     */
    @Test
    fun incompleteFormulaIsCalculated() {
        assertEquals(BigDecimal("3"), calculator.calculate("(1+2"))
        assertEquals(BigDecimal("3"), calculator.calculate("1+2*"))
        assertEquals(BigDecimal("0"), calculator.calculate(""))
    }

    /**
     * Verifies that a formula which cannot be made valid by the tolerance rule delivers no new result.
     */
    @Test
    fun uncorrectableFormulaDeliversNoResult() {
        assertNull(calculator.calculate("1&2"))
        assertNull(calculator.calculate("1+2)"))
    }

    /**
     * Verifies that a division by zero delivers no new result instead of failing.
     */
    @Test
    fun divisionByZeroDeliversNoResult() {
        assertNull(calculator.calculate("1/0"))
    }
}
