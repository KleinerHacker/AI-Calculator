package de.pcsoft.demo.ai.aicalculator.window

import de.pcsoft.demo.ai.aicalculator.component.formula.Formula
import de.pcsoft.demo.ai.aicalculator.component.keypad.Keypad
import de.pcsoft.demo.ai.aicalculator.component.keypad.KeypadKey
import de.saxsys.mvvmfx.FxmlView
import de.saxsys.mvvmfx.InjectViewModel
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.BorderPane
import java.net.URL
import java.util.ResourceBundle

/**
 * Controller ("code behind") of the main window.
 *
 * Binds the FXML user interface to the associated [MainWindowViewModel] following the MVVM pattern.
 */
class MainWindowView : FxmlView<MainWindowViewModel>, Initializable {

    /** Root container of the user interface, injected from the FXML file. */
    @FXML
    private lateinit var root: BorderPane

    /** Formula display component, embedded and injected from the FXML file. */
    @FXML
    private lateinit var formula: Formula

    /** Keypad component, embedded and injected from the FXML file. */
    @FXML
    private lateinit var keypad: Keypad

    /** The main window's view model injected by mvvmfx. */
    @InjectViewModel
    private lateinit var viewModel: MainWindowViewModel

    /**
     * Initializes the controller after the FXML file has been loaded.
     *
     * Wires the keypad to the formula display, so that key presses modify the displayed formula.
     *
     * @param location the base URL of the FXML document or `null`.
     * @param resources the resource bundle used or `null`.
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        keypad.onKey = { key -> handle(key) }
    }

    /**
     * Applies a pressed keypad key to the formula display.
     *
     * @param key the pressed keypad key.
     */
    private fun handle(key: KeypadKey) {
        when (key) {
            KeypadKey.CLEAR -> formula.clear()
            KeypadKey.EQUALS -> formula.recalculate()
            else -> key.character?.let { formula.append(it) }
        }
    }
}
