package de.pcsoft.demo.ai.aicalculator.component.formula

import de.saxsys.mvvmfx.FluentViewLoader
import javafx.scene.layout.VBox
import java.util.ResourceBundle

/**
 * Standalone and embeddable formula display component.
 *
 * The component loads its own user interface from the associated FXML file and keeps its view
 * model completely private. It is addressed exclusively via its own API ([append], [clear] and
 * [recalculate]). Because it provides a no-argument constructor, it can also be embedded
 * directly from an FXML file.
 */
class Formula : VBox() {

    /** View model driving the formula display, owned exclusively by this component. */
    private val viewModel: FormulaViewModel

    init {
        val viewTuple = FluentViewLoader.fxmlView(FormulaView::class.java)
            .root(this)
            .codeBehind(FormulaView())
            .resourceBundle(ResourceBundle.getBundle(MESSAGES_BUNDLE))
            .load()
        viewModel = viewTuple.viewModel
    }

    /**
     * Appends the given character to the displayed formula.
     *
     * @param character the character entered by the user.
     */
    fun append(character: Char) {
        viewModel.append(character)
    }

    /**
     * Resets the displayed formula to a blank formula.
     */
    fun clear() {
        viewModel.clear()
    }

    /**
     * Forces a recomputation of the displayed result without changing the formula.
     */
    fun recalculate() {
        viewModel.recalculate()
    }

    private companion object {

        /** Base path of the i18n resource bundle. */
        const val MESSAGES_BUNDLE = "messages"
    }
}
