package de.pcsoft.demo.ai.aicalculator.window

import de.saxsys.mvvmfx.FluentViewLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import java.util.ResourceBundle

/**
 * Standalone main window of the application.
 *
 * The window loads its user interface via the MVVM framework (mvvmfx) from the
 * associated FXML file and is used as a standalone [Stage] object.
 */
class MainWindow : Stage() {

    init {
        val bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE)
        val viewTuple = FluentViewLoader.fxmlView(MainWindowView::class.java)
            .resourceBundle(bundle)
            .load()

        titleProperty().bind(viewTuple.viewModel.titleProperty())
        scene = Scene(viewTuple.view).apply {
            stylesheets.add(loadStylesheet())
        }
        icons.setAll(loadIcons())
        isResizable = false
        isIconified = false
    }

    /**
     * Resolves the application stylesheet from the resources.
     *
     * @return the external form of the stylesheet URL.
     */
    private fun loadStylesheet(): String {
        val resource = requireNotNull(javaClass.getResource("/$STYLESHEET")) {
            "Stylesheet resource /$STYLESHEET was not found"
        }
        return resource.toExternalForm()
    }

    /**
     * Loads all resolutions of the application icon from the resources.
     *
     * @return the list of loaded icon images.
     */
    private fun loadIcons(): List<Image> =
        ICON_SIZES.map { size ->
            val resource = requireNotNull(javaClass.getResourceAsStream("/$ICON_BASE@$size.png")) {
                "Icon resource /$ICON_BASE@$size.png was not found"
            }
            Image(resource)
        }

    private companion object {

        /** Base path of the i18n resource bundle. */
        const val MESSAGES_BUNDLE = "messages"

        /** Resource path of the application stylesheet. */
        const val STYLESHEET = "css/application.css"

        /** Resource base path of the application icon without resolution and extension. */
        const val ICON_BASE = "icons/app"

        /** Available resolutions of the application icon. */
        val ICON_SIZES = listOf(16, 24, 32, 48, 64)
    }
}
