package de.pcsoft.demo.ai.aicalculator.component.formula

import de.saxsys.mvvmfx.FxmlView
import de.saxsys.mvvmfx.InjectViewModel
import javafx.beans.value.ObservableValue
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.control.OverrunStyle
import javafx.scene.control.Tooltip
import java.net.URL
import java.util.ResourceBundle

/**
 * Controller ("code behind") of the standalone and embeddable formula display component.
 *
 * Shows the current formula in small text and, below it, the permanently updated result in
 * large text. Both texts are truncated at their beginning if the available space is too small,
 * so the end of the text always stays visible, and are fully available via tooltip.
 */
class FormulaView : FxmlView<FormulaViewModel>, Initializable {

    /** Label showing the current formula in small text. */
    @FXML
    private lateinit var formulaLabel: Label

    /** Label showing the current result in large text. */
    @FXML
    private lateinit var resultLabel: Label

    /** The formula display's view model injected by mvvmfx. */
    @InjectViewModel
    private lateinit var viewModel: FormulaViewModel

    /**
     * Initializes the controller after the FXML file has been loaded.
     *
     * @param location the base URL of the FXML document or `null`.
     * @param resources the resource bundle used or `null`.
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        bind(formulaLabel, viewModel.formulaText)
        bind(resultLabel, viewModel.result)
    }

    /**
     * Binds the given label to a text property and configures its truncation behaviour.
     *
     * @param label the label to configure.
     * @param text the property providing the label text.
     */
    private fun bind(label: Label, text: ObservableValue<String>) {
        label.textProperty().bind(text)
        label.isWrapText = false
        label.textOverrun = OverrunStyle.LEADING_ELLIPSIS
        label.minWidth = 0.0

        val tooltip = Tooltip()
        tooltip.textProperty().bind(label.textProperty())
        label.tooltip = tooltip
    }
}
