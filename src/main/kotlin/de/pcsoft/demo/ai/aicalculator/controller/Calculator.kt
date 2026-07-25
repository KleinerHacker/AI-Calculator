package de.pcsoft.demo.ai.aicalculator.controller

import java.math.BigDecimal
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

/**
 * Calculator that evaluates a formula given as a string.
 *
 * The formula is first made valid by the [FormulaTolerance] rule and afterwards evaluated by the Kotlin script
 * engine (KTS). All arithmetic is performed on [BigDecimal] to support arbitrary numbers.
 */
class Calculator {

    /**
     * Calculates the result of the given formula.
     *
     * @param formula the formula as displayed to the user, may be incomplete or corrupt.
     * @return the result of the formula, or `null` if the formula cannot be evaluated, which signals that there
     *         is no new result.
     */
    fun calculate(formula: String): BigDecimal? {
        val corrected = FormulaTolerance.apply(formula) ?: return null
        val script = PRELUDE + "(" + NUMBER_LITERAL.replace(corrected) { "N(BigDecimal(\"${it.value}\"))" } +
                ").value.toPlainString()"

        return try {
            val result = engine?.eval(script) as? String ?: return null
            BigDecimal(result)
        } catch (_: Exception) {
            null
        }
    }

    private companion object {

        /** Matcher for all number literals of a formula. */
        private val NUMBER_LITERAL = Regex("""\d+(\.\d+)?""")

        /** Script prelude that provides arbitrary precision arithmetic for the evaluated formula. */
        private val PRELUDE = """
            import java.math.BigDecimal
            import java.math.MathContext

            class N(val value: BigDecimal) {
                operator fun plus(other: N) = N(value.add(other.value))
                operator fun minus(other: N) = N(value.subtract(other.value))
                operator fun times(other: N) = N(value.multiply(other.value))
                operator fun div(other: N) = N(value.divide(other.value, MathContext.DECIMAL128).stripTrailingZeros())
                operator fun unaryPlus() = this
                operator fun unaryMinus() = N(value.negate())
            }

        """.trimIndent()

        /** Lazily created and reused Kotlin script engine. */
        private val engine: ScriptEngine? by lazy {
            ScriptEngineManager(Calculator::class.java.classLoader).getEngineByExtension("kts")
        }
    }
}
