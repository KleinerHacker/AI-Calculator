package de.pcsoft.demo.ai.aicalculator.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests for the [FormulaInput] input rule.
 *
 * The tests cover the replacement of a trailing operator, the replacement of a trailing decimal
 * point, the strict separation of both character classes and the plain append of all other
 * characters.
 */
class FormulaInputTest {

    /**
     * Verifies that a digit entered into an empty formula becomes the first character of the
     * formula, because there is no previous character that could be replaced.
     */
    @Test
    fun digitOnEmptyFormulaStartsTheFormula() {
        assertEquals("1", FormulaInput.append("", '1'))
    }

    /**
     * Verifies that an operator entered into an empty formula is appended as well, so a formula
     * may start with a sign.
     */
    @Test
    fun operatorOnEmptyFormulaIsAppended() {
        assertEquals("-", FormulaInput.append("", '-'))
    }

    /**
     * Verifies that a digit is always appended, even if the formula already ends with a digit,
     * because digits form multi-digit numbers.
     */
    @Test
    fun digitIsAlwaysAppended() {
        assertEquals("123", FormulaInput.append("12", '3'))
    }

    /**
     * Verifies that an operator is appended after a digit, because there is no trailing operator
     * that would have to be replaced.
     */
    @Test
    fun operatorAfterDigitIsAppended() {
        assertEquals("12+", FormulaInput.append("12", '+'))
    }

    /**
     * Verifies that entering the same operator twice keeps only the last entered operator.
     */
    @Test
    fun repeatedOperatorReplacesItself() {
        assertEquals("1+", FormulaInput.append("1+", '+'))
    }

    /**
     * Verifies that a different operator entered after a trailing operator replaces it, so
     * `1+` followed by `*` becomes `1*`.
     */
    @Test
    fun differentOperatorReplacesTrailingOperator() {
        assertEquals("1*", FormulaInput.append("1+", '*'))
        assertEquals("1-", FormulaInput.append("1*", '-'))
        assertEquals("1/", FormulaInput.append("1-", '/'))
    }

    /**
     * Verifies that a decimal point entered after a digit is appended, because it starts the
     * fraction of the current number.
     */
    @Test
    fun decimalPointAfterDigitIsAppended() {
        assertEquals("1.", FormulaInput.append("1", '.'))
    }

    /**
     * Verifies that a decimal point entered after a trailing decimal point replaces it, so only
     * the last entered decimal point counts.
     */
    @Test
    fun repeatedDecimalPointReplacesItself() {
        assertEquals("1.", FormulaInput.append("1.", '.'))
    }

    /**
     * Verifies that a decimal point does not replace a trailing operator, because both character
     * classes are treated separately.
     */
    @Test
    fun decimalPointDoesNotReplaceOperator() {
        assertEquals("1+.", FormulaInput.append("1+", '.'))
    }

    /**
     * Verifies that an operator does not replace a trailing decimal point, because both character
     * classes are treated separately.
     */
    @Test
    fun operatorDoesNotReplaceDecimalPoint() {
        assertEquals("1.+", FormulaInput.append("1.", '+'))
    }

    /**
     * Verifies that a decimal point entered into an empty formula is appended, so an incomplete
     * decimal number can be started and later be tolerated by [FormulaTolerance].
     */
    @Test
    fun decimalPointOnEmptyFormulaIsAppended() {
        assertEquals(".", FormulaInput.append("", '.'))
    }

    /**
     * Verifies that a longer sequence of inputs including several repeated operators results in
     * the expected formula, so the rule also holds when applied repeatedly.
     */
    @Test
    fun sequenceOfInputsKeepsOnlyTheLastOperator() {
        val formula = "12+-*3".fold("") { current, character -> FormulaInput.append(current, character) }

        assertEquals("12*3", formula)
    }
}
