package de.pcsoft.demo.ai.aicalculator.window

import java.util.Locale
import java.util.ResourceBundle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests for the [MainWindowViewModel].
 *
 * The view model is created without the MVVM framework: the resource bundle that mvvmfx would
 * inject is set reflectively.
 */
class MainWindowViewModelTest {

    private lateinit var viewModel: MainWindowViewModel

    /**
     * Creates a view model without an injected resource bundle, so every test can inject the
     * bundle of the locale it needs.
     */
    @BeforeEach
    fun setUp() {
        viewModel = MainWindowViewModel()
    }

    /**
     * Verifies that the window title is taken from the default resource bundle and is not blank,
     * so the window is never rendered without a title.
     */
    @Test
    fun titleIsTakenFromTheDefaultBundle() {
        val bundle = inject(Locale.ENGLISH)

        assertEquals(bundle.getString(KEY_WINDOW_TITLE), viewModel.titleProperty().get())
        assertFalse(viewModel.titleProperty().get().isBlank())
    }

    /**
     * Verifies that the window title follows the injected resource bundle, so the German title is
     * used for a German locale.
     */
    @Test
    fun titleFollowsTheInjectedBundle() {
        val bundle = inject(Locale.GERMAN)

        assertEquals(bundle.getString(KEY_WINDOW_TITLE), viewModel.titleProperty().get())
    }

    /**
     * Verifies that the title property is read-only and always the same instance, so a view may
     * bind to it safely without being able to overwrite the title.
     */
    @Test
    fun titlePropertyIsStableAndReadOnly() {
        inject(Locale.ENGLISH)

        assertSame(viewModel.titleProperty(), viewModel.titleProperty())
    }

    /**
     * Injects the resource bundle of the given locale into the view model, replacing what mvvmfx
     * would do at runtime.
     *
     * @param locale the locale whose bundle is injected.
     * @return the injected resource bundle.
     */
    private fun inject(locale: Locale): ResourceBundle {
        val bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE, locale)

        val field = MainWindowViewModel::class.java.getDeclaredField("resources")
        field.isAccessible = true
        field.set(viewModel, bundle)

        return bundle
    }

    private companion object {

        /** Base path of the i18n resource bundle. */
        const val MESSAGES_BUNDLE = "messages"

        /** Bundle key of the window title. */
        const val KEY_WINDOW_TITLE = "window.title"
    }
}
