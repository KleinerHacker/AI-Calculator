package de.pcsoft.demo.ai.aicalculator.component.formula

import de.pcsoft.demo.ai.aicalculator.controller.Calculator
import de.saxsys.mvvmfx.InjectResourceBundle
import de.saxsys.mvvmfx.ViewModel
import javafx.beans.binding.Bindings
import javafx.beans.binding.StringBinding
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import java.util.ResourceBundle

/**
 * View model of the [FormulaView] component.
 *
 * The formula itself is provided from outside via [formula], either by setting a value or by
 * binding an own property to it. The display text [formulaText] and the [result] are pure
 * bindings on that formula and are therefore recomputed automatically on every change. All
 * texts are obtained exclusively from the resource bundle.
 */
class FormulaViewModel : ViewModel {

    @InjectResourceBundle
    private lateinit var resources: ResourceBundle

    /** Calculator used to evaluate the current formula. */
    private val calculator = Calculator()

    /** The formula, provided from outside by the embedding component. */
    val formula: StringProperty = SimpleStringProperty(this, "formula", "")

    /** Display text of the formula, yielding the placeholder text for a blank formula. */
    val formulaText: StringBinding = Bindings.createStringBinding(
        {
            val current = formula.get() ?: ""
            if (current.isBlank()) resources.getString(KEY_FORMULA_PLACEHOLDER) else current
        },
        formula
    )

    /**
     * Display text of the current result, yielding the placeholder text as long as the formula
     * is blank or cannot be evaluated.
     */
    val result: StringBinding = Bindings.createStringBinding(
        {
            val current = formula.get() ?: ""
            if (current.isBlank()) resources.getString(KEY_RESULT_PLACEHOLDER)
            else calculator.calculate(current)?.toPlainString()
                ?: resources.getString(KEY_RESULT_PLACEHOLDER)
        },
        formula
    )

    private companion object {

        /** Bundle key of the placeholder shown instead of a blank formula. */
        const val KEY_FORMULA_PLACEHOLDER = "formula.placeholder"

        /** Bundle key of the placeholder shown instead of a missing result. */
        const val KEY_RESULT_PLACEHOLDER = "formula.result.placeholder"
    }
}
