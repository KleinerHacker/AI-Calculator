package de.pcsoft.demo.ai.aicalculator

import de.pcsoft.demo.ai.aicalculator.window.MainWindow
import javafx.application.Application
import javafx.stage.Stage

/**
 * Entry point of the JavaFX application.
 *
 * The application deliberately does not use the primary stage provided by JavaFX,
 * but creates the main window [MainWindow] as a standalone stage object.
 */
class AiCalculatorApplication : Application() {

    /**
     * Starts the application by creating and showing the standalone main window.
     *
     * @param primaryStage the primary stage provided by JavaFX; intentionally left unused.
     */
    override fun start(primaryStage: Stage) {
        println("KI-Rechner wird gestartet …")
        MainWindow().show()
    }
}

/**
 * Process entry point of the application.
 *
 * @param args the command line arguments forwarded to JavaFX.
 */
fun main(args: Array<String>) {
    Application.launch(AiCalculatorApplication::class.java, *args)
}
