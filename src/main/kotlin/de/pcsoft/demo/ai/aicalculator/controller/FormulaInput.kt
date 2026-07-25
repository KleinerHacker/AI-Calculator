package de.pcsoft.demo.ai.aicalculator.controller

/**
 * Input rule that extends a formula by a single character entered by the user.
 *
 * The rule only looks at the last character of the formula built so far. If the user enters
 * several arithmetic operators or several decimal points in a row, only the last entered
 * character counts, because the previous one is replaced.
 */
object FormulaInput {

    /** All characters that are treated as arithmetic operators. */
    private const val OPERATORS = "+-*/"

    /** Character separating the integer part of a number from its fraction. */
    private const val DECIMAL_POINT = '.'

    /**
     * Appends the given character to the given formula.
     *
     * An operator entered while the formula already ends with an operator replaces that
     * operator, even if both operators differ (`1+` plus `*` becomes `1*`). The same applies
     * to a decimal point entered while the formula already ends with a decimal point. An
     * operator never replaces a decimal point and a decimal point never replaces an operator.
     *
     * @param formula the formula built so far.
     * @param character the character entered by the user.
     * @return the extended formula.
     */
    fun append(formula: String, character: Char): String {
        val previous = formula.lastOrNull()
        val replaces = when (character) {
            in OPERATORS -> previous != null && previous in OPERATORS
            DECIMAL_POINT -> previous == DECIMAL_POINT
            else -> false
        }

        return if (replaces) formula.dropLast(1) + character else formula + character
    }
}
