package de.pcsoft.demo.ai.aicalculator.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests for the tolerance rule [FormulaTolerance].
 */
class FormulaToleranceTest {

    /**
     * Verifies that an already valid formula is returned unchanged and that surrounding whitespace is removed.
     */
    @Test
    fun validFormulaRemainsUnchanged() {
        assertEquals("1+2*(3-4)", FormulaTolerance.apply(" 1 + 2 * (3 - 4) "))
    }

    /**
     * Verifies that an empty formula and a formula consisting of whitespace only are corrected to zero.
     */
    @Test
    fun emptyFormulaBecomesZero() {
        assertEquals("0", FormulaTolerance.apply(""))
        assertEquals("0", FormulaTolerance.apply("   "))
    }

    /**
     * Verifies that a formula containing a character that is not part of a formula cannot be corrected.
     */
    @Test
    fun unknownCharacterCannotBeCorrected() {
        assertNull(FormulaTolerance.apply("1&2"))
    }

    /**
     * Verifies that duplicated operators are shortened so that the first operator wins.
     */
    @Test
    fun duplicatedOperatorIsShortened() {
        assertEquals("2+1", FormulaTolerance.apply("2+*1"))
        assertEquals("2*1", FormulaTolerance.apply("2*/1"))
    }

    /**
     * Verifies that the sign combinations `+-` and `-+` are kept because they change the sign of the next term.
     */
    @Test
    fun signCombinationIsKept() {
        assertEquals("2+-1", FormulaTolerance.apply("2+-1"))
        assertEquals("2-+1", FormulaTolerance.apply("2-+1"))
    }

    /**
     * Verifies that a multiplication or division operator is removed if it cannot start a term, while a leading
     * sign is kept.
     */
    @Test
    fun operatorWithoutLeftTermIsRemoved() {
        assertEquals("1", FormulaTolerance.apply("*1"))
        assertEquals("(1+2)", FormulaTolerance.apply("(/1+2)"))
        assertEquals("-1", FormulaTolerance.apply("-1"))
    }

    /**
     * Verifies that operators at the end of the formula are cut off.
     */
    @Test
    fun trailingOperatorIsCutOff() {
        assertEquals("1+2", FormulaTolerance.apply("1+2*"))
        assertEquals("0", FormulaTolerance.apply("+"))
    }

    /**
     * Verifies that an incomplete decimal number is completed at the beginning and at the end.
     */
    @Test
    fun incompleteDecimalNumberIsCompleted() {
        assertEquals("5", FormulaTolerance.apply("5."))
        assertEquals("0.5", FormulaTolerance.apply(".5"))
        assertEquals("0", FormulaTolerance.apply("."))
    }

    /**
     * Verifies that open parentheses are closed at the end of the formula, including an operator directly in
     * front of the inserted parenthesis.
     */
    @Test
    fun openParenthesisIsClosed() {
        assertEquals("(1+2)", FormulaTolerance.apply("(1+2"))
        assertEquals("((1+2))", FormulaTolerance.apply("((1+2"))
        assertEquals("(1)", FormulaTolerance.apply("(1+"))
    }

    /**
     * Verifies that a closing parenthesis without a matching opening parenthesis cannot be corrected.
     */
    @Test
    fun excessClosingParenthesisCannotBeCorrected() {
        assertNull(FormulaTolerance.apply("1+2)"))
        assertNull(FormulaTolerance.apply(")"))
    }

    /**
     * Verifies that empty parentheses are removed completely, also if they only contain an operator.
     */
    @Test
    fun emptyParenthesisIsRemoved() {
        assertEquals("0", FormulaTolerance.apply("()"))
        assertEquals("1", FormulaTolerance.apply("1*(+)"))
        assertEquals("(1)", FormulaTolerance.apply("(1)()"))
    }

    /**
     * Verifies that an operator directly in front of a closing parenthesis is removed.
     */
    @Test
    fun operatorInFrontOfClosingParenthesisIsRemoved() {
        assertEquals("(1+2)", FormulaTolerance.apply("(1+2*)"))
    }
}
