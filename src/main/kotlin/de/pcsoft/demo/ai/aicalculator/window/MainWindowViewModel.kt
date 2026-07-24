package de.pcsoft.demo.ai.aicalculator.window

import de.saxsys.mvvmfx.InjectResourceBundle
import de.saxsys.mvvmfx.ViewModel
import javafx.beans.property.ReadOnlyStringProperty
import javafx.beans.property.ReadOnlyStringWrapper
import java.util.ResourceBundle

/**
 * View model of the main window.
 *
 * Holds the input/output properties required to render the main window.
 * All texts are obtained exclusively from the resource bundle.
 */
class MainWindowViewModel : ViewModel {

    @InjectResourceBundle
    private lateinit var resources: ResourceBundle

    private val title: ReadOnlyStringWrapper by lazy {
        ReadOnlyStringWrapper(resources.getString("window.title"))
    }

    /**
     * Returns the read-only property of the window title (text from the resource bundle).
     *
     * @return the observable, read-only title property.
     */
    fun titleProperty(): ReadOnlyStringProperty = title.readOnlyProperty
}
