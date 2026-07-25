package de.pcsoft.demo.ai.aicalculator.component.formula

import java.util.ResourceBundle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests for the [FormulaViewModel].
 *
 * The view model is created without the MVVM framework: the resource bundle that mvvmfx would
 * inject is set reflectively. No further initialization is needed, because the derived texts are
 * lazily evaluated bindings.
 */
class FormulaViewModelTest {

    private lateinit var bundle: ResourceBundle

    private lateinit var viewModel: FormulaViewModel

    /**
     * Creates a view model with the application resource bundle injected.
     */
    @BeforeEach
    fun setUp() {
        bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE)
        viewModel = FormulaViewModel()

        val field = FormulaViewModel::class.java.getDeclaredField("resources")
        field.isAccessible = true
        field.set(viewModel, bundle)
    }

    /**
     * Verifies that a view model without any input shows the placeholder texts for both the
     * formula and the result, because no formula has been provided from outside yet.
     */
    @Test
    fun initialStateShowsPlaceholders() {
        assertEquals("", viewModel.formula.get())
        assertEquals(bundle.getString(KEY_FORMULA_PLACEHOLDER), viewModel.formulaText.get())
        assertEquals(bundle.getString(KEY_RESULT_PLACEHOLDER), viewModel.result.get())
    }

    /**
     * Verifies that a valid formula is displayed unchanged and that its result is calculated.
     */
    @Test
    fun validFormulaIsDisplayedAndCalculated() {
        viewModel.formula.set("1+2*3")

        assertEquals("1+2*3", viewModel.formulaText.get())
        assertEquals("7", viewModel.result.get())
    }

    /**
     * Verifies that an incomplete formula ending with an operator stays visible while the result
     * is derived from the tolerated part of the formula.
     */
    @Test
    fun incompleteFormulaStaysVisible() {
        viewModel.formula.set("12+")

        assertEquals("12+", viewModel.formulaText.get())
        assertEquals("12", viewModel.result.get())
    }

    /**
     * Verifies that a formula which cannot be evaluated at all keeps being displayed unchanged
     * while the result falls back to the placeholder text.
     */
    @Test
    fun corruptFormulaKeepsFormulaAndClearsResult() {
        viewModel.formula.set("1/0")

        assertEquals("1/0", viewModel.formulaText.get())
        assertEquals(bundle.getString(KEY_RESULT_PLACEHOLDER), viewModel.result.get())
    }

    /**
     * Verifies that clearing an already entered formula switches both texts back to their
     * placeholders, and that a blank formula is treated like an empty one.
     */
    @Test
    fun blankFormulaFallsBackToPlaceholders() {
        viewModel.formula.set("1+2")
        viewModel.formula.set("   ")

        assertEquals(bundle.getString(KEY_FORMULA_PLACEHOLDER), viewModel.formulaText.get())
        assertEquals(bundle.getString(KEY_RESULT_PLACEHOLDER), viewModel.result.get())
    }

    /**
     * Verifies that a formula set to `null` is handled like an empty formula instead of failing.
     */
    @Test
    fun nullFormulaIsHandledLikeEmptyFormula() {
        viewModel.formula.set(null)

        assertEquals(bundle.getString(KEY_FORMULA_PLACEHOLDER), viewModel.formulaText.get())
        assertEquals(bundle.getString(KEY_RESULT_PLACEHOLDER), viewModel.result.get())
    }

    /**
     * Verifies that the bindings are invalidated by every change of the formula, so the displayed
     * result is available at all times without an explicit trigger.
     */
    @Test
    fun everyFormulaChangeUpdatesTheResult() {
        viewModel.formula.set("2*3")
        assertEquals("6", viewModel.result.get())

        viewModel.formula.set("2*3+4")
        assertEquals("10", viewModel.result.get())
    }

    /**
     * Verifies that arbitrarily large numbers are displayed in plain notation instead of the
     * scientific notation of a floating point calculation.
     */
    @Test
    fun largeResultIsDisplayedInPlainNotation() {
        viewModel.formula.set("99999999999999999999*99999999999999999999")

        assertEquals("9999999999999999999800000000000000000001", viewModel.result.get())
    }

    private companion object {

        /** Base path of the i18n resource bundle. */
        const val MESSAGES_BUNDLE = "messages"

        /** Bundle key of the placeholder shown instead of a blank formula. */
        const val KEY_FORMULA_PLACEHOLDER = "formula.placeholder"

        /** Bundle key of the placeholder shown instead of a missing result. */
        const val KEY_RESULT_PLACEHOLDER = "formula.result.placeholder"
    }
}
