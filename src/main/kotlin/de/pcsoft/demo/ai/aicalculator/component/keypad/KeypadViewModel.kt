package de.pcsoft.demo.ai.aicalculator.component.keypad

import de.saxsys.mvvmfx.InjectResourceBundle
import de.saxsys.mvvmfx.ViewModel
import java.util.ResourceBundle

/**
 * View model of the [KeypadView] component.
 *
 * Provides the descriptive texts of all keypad keys. All texts are obtained
 * exclusively from the resource bundle.
 */
class KeypadViewModel : ViewModel {

    @InjectResourceBundle
    private lateinit var resources: ResourceBundle

    /**
     * Returns the localized description of the given key.
     *
     * The description contains the symbol, its meaning and the keyboard shortcut and is used
     * as tooltip as well as accessible text.
     *
     * @param key the key whose description is requested.
     * @return the localized description text.
     */
    fun description(key: KeypadKey): String = resources.getString(key.bundleKey)
}
