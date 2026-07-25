package de.pcsoft.demo.ai.aicalculator.component.keypad

import java.util.ResourceBundle
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests for the [KeypadViewModel] and the [KeypadKey] definitions.
 *
 * The view model is created without the MVVM framework: the resource bundle that mvvmfx would
 * inject is set reflectively.
 */
class KeypadViewModelTest {

    private lateinit var bundle: ResourceBundle

    private lateinit var viewModel: KeypadViewModel

    /**
     * Creates a view model with the application resource bundle injected.
     */
    @BeforeEach
    fun setUp() {
        bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE)
        viewModel = KeypadViewModel()

        val field = KeypadViewModel::class.java.getDeclaredField("resources")
        field.isAccessible = true
        field.set(viewModel, bundle)
    }

    /**
     * Verifies that every key provides a non-blank description from the resource bundle, so all
     * tooltips and accessible texts are localized.
     */
    @Test
    fun everyKeyHasADescription() {
        KeypadKey.entries.forEach { key ->
            assertEquals(bundle.getString(key.bundleKey), viewModel.description(key))
            assertFalse(viewModel.description(key).isBlank())
        }
    }

    /**
     * Verifies that the default key handler does nothing and especially does not fail, so a
     * keypad that has not been wired up yet stays usable.
     */
    @Test
    fun defaultHandlerDoesNothing() {
        assertDoesNotThrow { KeypadKey.entries.forEach { key -> viewModel.onKey(key) } }
    }

    /**
     * Verifies that a handler installed from outside receives exactly the pressed key, so the
     * embedding window can dispatch the key press.
     */
    @Test
    fun installedHandlerReceivesEveryKey() {
        val pressed = mutableListOf<KeypadKey>()
        viewModel.onKey = { key -> pressed += key }

        KeypadKey.entries.forEach { key -> viewModel.onKey(key) }

        assertEquals(KeypadKey.entries.toList(), pressed)
    }

    /**
     * Verifies that a handler can be replaced, so only the most recently installed handler is
     * notified about a key press.
     */
    @Test
    fun handlerCanBeReplaced() {
        val first = mutableListOf<KeypadKey>()
        val second = mutableListOf<KeypadKey>()
        viewModel.onKey = { key -> first += key }
        viewModel.onKey = { key -> second += key }

        viewModel.onKey(KeypadKey.DIGIT_7)

        assertEquals(emptyList<KeypadKey>(), first)
        assertEquals(listOf(KeypadKey.DIGIT_7), second)
    }

    /**
     * Verifies that all keys contributing a character to the formula carry exactly that
     * character, so the dispatching in the main window does not need its own mapping.
     */
    @Test
    fun characterKeysCarryTheirCharacter() {
        assertEquals('0', KeypadKey.DIGIT_0.character)
        assertEquals('1', KeypadKey.DIGIT_1.character)
        assertEquals('2', KeypadKey.DIGIT_2.character)
        assertEquals('3', KeypadKey.DIGIT_3.character)
        assertEquals('4', KeypadKey.DIGIT_4.character)
        assertEquals('5', KeypadKey.DIGIT_5.character)
        assertEquals('6', KeypadKey.DIGIT_6.character)
        assertEquals('7', KeypadKey.DIGIT_7.character)
        assertEquals('8', KeypadKey.DIGIT_8.character)
        assertEquals('9', KeypadKey.DIGIT_9.character)
        assertEquals('+', KeypadKey.ADD.character)
        assertEquals('-', KeypadKey.SUBTRACT.character)
        assertEquals('*', KeypadKey.MULTIPLY.character)
        assertEquals('/', KeypadKey.DIVIDE.character)
    }

    /**
     * Verifies that the command keys carry no character, because they trigger an action instead
     * of extending the formula.
     */
    @Test
    fun commandKeysCarryNoCharacter() {
        assertNull(KeypadKey.EQUALS.character)
        assertNull(KeypadKey.CLEAR.character)
    }

    /**
     * Verifies that the icon resource of every key exists, so no key is rendered without its
     * graphic.
     */
    @Test
    fun everyKeyHasAnExistingIcon() {
        KeypadKey.entries.forEach { key ->
            assertNotNull(javaClass.getResource("/icons/${key.iconName}"), "Icon of $key is missing")
        }
    }

    private companion object {

        /** Base path of the i18n resource bundle. */
        const val MESSAGES_BUNDLE = "messages"
    }
}
