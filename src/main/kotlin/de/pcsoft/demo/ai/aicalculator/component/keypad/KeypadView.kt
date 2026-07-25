package de.pcsoft.demo.ai.aicalculator.component.keypad

import de.saxsys.mvvmfx.FxmlView
import de.saxsys.mvvmfx.InjectViewModel
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Tooltip
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.GridPane
import java.net.URL
import java.util.ResourceBundle

/**
 * Controller ("code behind") of the standalone and embeddable keypad component.
 *
 * Renders all calculator keys (digits, operators, result and clear) as icon-only buttons and
 * additionally makes them available via the physical keyboard. Every key press is reported to the
 * outside via the view model.
 */
class KeypadView : FxmlView<KeypadViewModel>, Initializable {

    /** Root container of the keypad, injected from the FXML file. */
    @FXML
    private lateinit var root: GridPane

    @FXML
    private lateinit var digit0Button: Button

    @FXML
    private lateinit var digit1Button: Button

    @FXML
    private lateinit var digit2Button: Button

    @FXML
    private lateinit var digit3Button: Button

    @FXML
    private lateinit var digit4Button: Button

    @FXML
    private lateinit var digit5Button: Button

    @FXML
    private lateinit var digit6Button: Button

    @FXML
    private lateinit var digit7Button: Button

    @FXML
    private lateinit var digit8Button: Button

    @FXML
    private lateinit var digit9Button: Button

    @FXML
    private lateinit var addButton: Button

    @FXML
    private lateinit var subtractButton: Button

    @FXML
    private lateinit var multiplyButton: Button

    @FXML
    private lateinit var divideButton: Button

    @FXML
    private lateinit var equalsButton: Button

    @FXML
    private lateinit var clearButton: Button

    /** The keypad's view model injected by mvvmfx. */
    @InjectViewModel
    private lateinit var viewModel: KeypadViewModel

    /** Mapping of all keys to their buttons, filled during initialization. */
    private lateinit var buttons: Map<KeypadKey, Button>

    /**
     * Initializes the controller after the FXML file has been loaded.
     *
     * @param location the base URL of the FXML document or `null`.
     * @param resources the resource bundle used or `null`.
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        buttons = mapOf(
            KeypadKey.DIGIT_0 to digit0Button,
            KeypadKey.DIGIT_1 to digit1Button,
            KeypadKey.DIGIT_2 to digit2Button,
            KeypadKey.DIGIT_3 to digit3Button,
            KeypadKey.DIGIT_4 to digit4Button,
            KeypadKey.DIGIT_5 to digit5Button,
            KeypadKey.DIGIT_6 to digit6Button,
            KeypadKey.DIGIT_7 to digit7Button,
            KeypadKey.DIGIT_8 to digit8Button,
            KeypadKey.DIGIT_9 to digit9Button,
            KeypadKey.ADD to addButton,
            KeypadKey.SUBTRACT to subtractButton,
            KeypadKey.MULTIPLY to multiplyButton,
            KeypadKey.DIVIDE to divideButton,
            KeypadKey.EQUALS to equalsButton,
            KeypadKey.CLEAR to clearButton
        )

        buttons.forEach { (key, button) -> decorate(key, button) }
        installKeyboardSupport()
    }

    /**
     * Applies icon, tooltip and accessible text to the button of the given key and reports its
     * activation to the view model.
     *
     * @param key the key represented by the button.
     * @param button the button to decorate.
     */
    private fun decorate(key: KeypadKey, button: Button) {
        val resource = requireNotNull(javaClass.getResourceAsStream("/$ICON_FOLDER/${key.iconName}")) {
            "Icon resource /$ICON_FOLDER/${key.iconName} was not found"
        }
        val description = viewModel.description(key)

        button.text = null
        button.graphic = ImageView(Image(resource))
        button.tooltip = Tooltip(description)
        button.accessibleText = description
        button.isFocusTraversable = false
        button.setOnAction { viewModel.onKey(key) }
    }

    /**
     * Registers the keyboard support as soon as the component becomes part of a scene.
     */
    private fun installKeyboardSupport() {
        root.sceneProperty().addListener { _, oldScene, newScene ->
            oldScene?.removeEventFilter(KeyEvent.KEY_PRESSED, ::handleKeyPressed)
            oldScene?.removeEventFilter(KeyEvent.KEY_TYPED, ::handleKeyTyped)
            newScene?.addEventFilter(KeyEvent.KEY_PRESSED, ::handleKeyPressed)
            newScene?.addEventFilter(KeyEvent.KEY_TYPED, ::handleKeyTyped)
        }
        root.scene?.let { scene: Scene ->
            scene.addEventFilter(KeyEvent.KEY_PRESSED, ::handleKeyPressed)
            scene.addEventFilter(KeyEvent.KEY_TYPED, ::handleKeyTyped)
        }
    }

    /**
     * Handles the non-printable keyboard shortcuts (`Enter`, `Esc` and `Delete`).
     *
     * @param event the observed key event.
     */
    private fun handleKeyPressed(event: KeyEvent) {
        val key = when (event.code) {
            KeyCode.ENTER -> KeypadKey.EQUALS
            KeyCode.ESCAPE, KeyCode.DELETE -> KeypadKey.CLEAR
            else -> null
        } ?: return

        fire(key, event)
    }

    /**
     * Handles the printable keyboard shortcuts (digits, operators and `=`).
     *
     * Using typed characters covers the numeric keypad as well as all keyboard layouts.
     *
     * @param event the observed key event.
     */
    private fun handleKeyTyped(event: KeyEvent) {
        val key = when (event.character) {
            "0" -> KeypadKey.DIGIT_0
            "1" -> KeypadKey.DIGIT_1
            "2" -> KeypadKey.DIGIT_2
            "3" -> KeypadKey.DIGIT_3
            "4" -> KeypadKey.DIGIT_4
            "5" -> KeypadKey.DIGIT_5
            "6" -> KeypadKey.DIGIT_6
            "7" -> KeypadKey.DIGIT_7
            "8" -> KeypadKey.DIGIT_8
            "9" -> KeypadKey.DIGIT_9
            "+" -> KeypadKey.ADD
            "-" -> KeypadKey.SUBTRACT
            "*" -> KeypadKey.MULTIPLY
            "/" -> KeypadKey.DIVIDE
            "=" -> KeypadKey.EQUALS
            else -> null
        } ?: return

        fire(key, event)
    }

    /**
     * Triggers the button of the given key and consumes the causing event.
     *
     * @param key the key to trigger.
     * @param event the event that caused the activation.
     */
    private fun fire(key: KeypadKey, event: KeyEvent) {
        buttons[key]?.fire()
        event.consume()
    }

    private companion object {

        /** Resource folder containing all key icons. */
        const val ICON_FOLDER = "icons"
    }
}
