package de.pcsoft.demo.ai.aicalculator.component.keypad

import de.saxsys.mvvmfx.FluentViewLoader
import javafx.scene.layout.GridPane
import java.util.ResourceBundle

/**
 * Standalone and embeddable keypad component.
 *
 * The component loads its own user interface from the associated FXML file and keeps its view
 * model completely private. It is addressed exclusively via its own API ([onKey]). Because it
 * provides a no-argument constructor, it can also be embedded directly from an FXML file.
 */
class Keypad : GridPane() {

    /** View model driving the keypad, owned exclusively by this component. */
    private val viewModel: KeypadViewModel

    init {
        val viewTuple = FluentViewLoader.fxmlView(KeypadView::class.java)
            .root(this)
            .codeBehind(KeypadView())
            .resourceBundle(ResourceBundle.getBundle(MESSAGES_BUNDLE))
            .load()
        viewModel = viewTuple.viewModel
    }

    /**
     * Callback invoked on every key press, no matter whether the key was activated via mouse or
     * via the physical keyboard. By default nothing happens.
     */
    var onKey: (KeypadKey) -> Unit
        get() = viewModel.onKey
        set(value) {
            viewModel.onKey = value
        }

    private companion object {

        /** Base path of the i18n resource bundle. */
        const val MESSAGES_BUNDLE = "messages"
    }
}
