package de.pcsoft.demo.ai.aicalculator.window

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

    /** The main window's view model injected by mvvmfx. */
    @InjectViewModel
    private lateinit var viewModel: MainWindowViewModel

    /**
     * Initializes the controller after the FXML file has been loaded.
     *
     * @param location the base URL of the FXML document or `null`.
     * @param resources the resource bundle used or `null`.
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // Base window: no bindings required yet.
    }
}
