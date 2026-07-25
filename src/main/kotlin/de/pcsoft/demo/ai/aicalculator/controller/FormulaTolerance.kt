package de.pcsoft.demo.ai.aicalculator.controller

/**
 * Tolerance rule that tries to turn a possibly incomplete or corrupt formula into a syntactically valid one.
 *
 * The rule never changes the mathematical meaning of the already valid part of a formula, it only removes or
 * completes characters that cannot be part of a valid formula.
 */
object FormulaTolerance {

    /** All characters a formula may consist of. */
    private const val ALLOWED_CHARACTERS = "0123456789.+-*/()"

    /** All characters that are treated as arithmetic operators. */
    private const val OPERATORS = "+-*/"

    /** Replacement of a decimal point that is not followed by a digit. */
    private val TRAILING_DECIMAL_POINT = Regex("""\.(?![0-9])""")

    /** Replacement of a decimal point that is not preceded by a digit. */
    private val LEADING_DECIMAL_POINT = Regex("""(?<![0-9])\.""")

    /**
     * Applies the tolerance rule to the given formula.
     *
     * The following corrections are performed:
     * - whitespace is removed and an empty formula becomes `0`
     * - duplicated operators are shortened, the first operator wins, except for `+-` and `-+`
     * - operators that cannot start a term are removed, trailing operators are cut off
     * - incomplete decimal numbers are completed (`.5` becomes `0.5`, `5.` becomes `5`)
     * - open parentheses are closed at the end, empty parentheses are removed
     *
     * @param formula the raw formula as entered by the user.
     * @return the corrected formula, or `null` if the formula cannot be made valid.
     */
    fun apply(formula: String): String? {
        val compacted = formula.filterNot { it.isWhitespace() }
        if (compacted.any { it !in ALLOWED_CHARACTERS }) {
            return null
        }

        val result = StringBuilder()
        var depth = 0
        for (character in compacted) {
            when {
                character in OPERATORS -> appendOperator(result, character)
                character == '(' -> {
                    result.append(character)
                    depth++
                }

                character == ')' -> {
                    cutTrailingOperators(result)
                    when {
                        depth == 0 -> return null
                        result.last() == '(' -> {
                            result.deleteCharAt(result.length - 1)
                            depth--
                        }

                        else -> {
                            result.append(character)
                            depth--
                        }
                    }
                }

                else -> result.append(character)
            }
        }

        cutTrailingOperators(result)
        repeat(depth) { result.append(')') }

        val corrected = LEADING_DECIMAL_POINT.replace(TRAILING_DECIMAL_POINT.replace(result, ""), "0.")
        return corrected.ifEmpty { "0" }
    }

    /**
     * Appends an operator to the formula built so far while applying the operator related tolerance rules.
     *
     * @param result the formula built so far.
     * @param operator the operator to append.
     */
    private fun appendOperator(result: StringBuilder, operator: Char) {
        val previous = result.lastOrNull()
        when {
            previous == null || previous == '(' -> if (operator == '+' || operator == '-') result.append(operator)
            previous in OPERATORS -> if (isSignCombination(previous, operator)) result.append(operator)
            else -> result.append(operator)
        }
    }

    /**
     * Checks whether the given operator pair forms a valid sign combination that must be kept.
     *
     * @param previous the operator already contained in the formula.
     * @param operator the operator to append.
     * @return `true` for `+-` and `-+`, otherwise `false`.
     */
    private fun isSignCombination(previous: Char?, operator: Char): Boolean =
        (previous == '+' && operator == '-') || (previous == '-' && operator == '+')

    /**
     * Removes all operators at the end of the formula built so far.
     *
     * @param result the formula built so far.
     */
    private fun cutTrailingOperators(result: StringBuilder) {
        while (result.isNotEmpty() && result.last() in OPERATORS) {
            result.deleteCharAt(result.length - 1)
        }
    }
}
