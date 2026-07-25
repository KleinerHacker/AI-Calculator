package de.pcsoft.demo.ai.aicalculator.component.formula

import java.util.ResourceBundle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests for the [FormulaViewModel].
 *
 * The view model is created without the MVVM framework: the resource bundle that mvvmfx would
 * inject is set reflectively. No further initialization is needed, because the derived texts are
 * lazily evaluated bindings. The formula is only fed character by character, exactly as the
 * keypad does it.
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
     * formula and the result, because no character has been entered yet.
     */
    @Test
    fun initialStateShowsPlaceholders() {
        assertEquals(bundle.getString(KEY_FORMULA_PLACEHOLDER), viewModel.formulaText.get())
        assertEquals(bundle.getString(KEY_RESULT_PLACEHOLDER), viewModel.result.get())
    }

    /**
     * Verifies that a valid formula entered character by character is displayed unchanged and
     * that its result is calculated.
     */
    @Test
    fun validFormulaIsDisplayedAndCalculated() {
        enter("1+2*3")

        assertEquals("1+2*3", viewModel.formulaText.get())
        assertEquals("7", viewModel.result.get())
    }

    /**
     * Verifies that an incomplete formula ending with an operator stays visible while the result
     * is derived from the tolerated part of the formula.
     */
    @Test
    fun incompleteFormulaStaysVisible() {
        enter("12+")

        assertEquals("12+", viewModel.formulaText.get())
        assertEquals("12", viewModel.result.get())
    }

    /**
     * Verifies that a formula which cannot be evaluated at all keeps being displayed unchanged
     * while the result falls back to the placeholder text.
     */
    @Test
    fun corruptFormulaKeepsFormulaAndClearsResult() {
        enter("1/0")

        assertEquals("1/0", viewModel.formulaText.get())
        assertEquals(bundle.getString(KEY_RESULT_PLACEHOLDER), viewModel.result.get())
    }

    /**
     * Verifies that clearing an already entered formula switches both texts back to their
     * placeholders.
     */
    @Test
    fun clearFallsBackToPlaceholders() {
        enter("1+2")
        viewModel.clear()

        assertEquals(bundle.getString(KEY_FORMULA_PLACEHOLDER), viewModel.formulaText.get())
        assertEquals(bundle.getString(KEY_RESULT_PLACEHOLDER), viewModel.result.get())
    }

    /**
     * Verifies that the bindings are invalidated by every entered character, so the displayed
     * result is available at all times without an explicit trigger.
     */
    @Test
    fun everyEnteredCharacterUpdatesTheResult() {
        enter("2*3")
        assertEquals("6", viewModel.result.get())

        enter("+4")
        assertEquals("10", viewModel.result.get())
    }

    /**
     * Verifies that entering several operators in a row keeps only the last entered operator,
     * because the previous one is replaced.
     */
    @Test
    fun severalOperatorsKeepOnlyTheLastOne() {
        enter("12+-*3")

        assertEquals("12*3", viewModel.formulaText.get())
        assertEquals("36", viewModel.result.get())
    }

    /**
     * Verifies that entering several decimal points in a row keeps only one decimal point, so a
     * valid decimal number results.
     */
    @Test
    fun severalDecimalPointsKeepOnlyOne() {
        enter("1...5")

        assertEquals("1.5", viewModel.formulaText.get())
        assertEquals("1.5", viewModel.result.get())
    }

    /**
     * Verifies that a forced recalculation invalidates the result binding, so pressing the
     * result key recomputes the result even though the formula did not change.
     */
    @Test
    fun recalculateInvalidatesTheResult() {
        enter("1+1")
        viewModel.result.get()

        var invalidated = false
        viewModel.result.addListener { _ -> invalidated = true }
        viewModel.recalculate()

        assertTrue(invalidated, "Result binding was not invalidated by recalculate()")
        assertEquals("2", viewModel.result.get())
    }

    /**
     * Verifies that a forced recalculation does not modify the formula itself, because the
     * result key is a pure command.
     */
    @Test
    fun recalculateKeepsTheFormula() {
        enter("1+1")
        viewModel.recalculate()

        assertEquals("1+1", viewModel.formulaText.get())
    }

    /**
     * Verifies that arbitrarily large numbers are displayed in plain notation instead of the
     * scientific notation of a floating point calculation.
     */
    @Test
    fun largeResultIsDisplayedInPlainNotation() {
        enter("99999999999999999999*99999999999999999999")

        assertEquals("9999999999999999999800000000000000000001", viewModel.result.get())
    }

    /**
     * Enters all characters of the given text one after another, simulating the keypad.
     *
     * @param characters the characters to enter.
     */
    private fun enter(characters: String) {
        characters.forEach { character -> viewModel.append(character) }
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
