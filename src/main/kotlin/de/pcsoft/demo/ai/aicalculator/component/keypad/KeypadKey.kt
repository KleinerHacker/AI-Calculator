package de.pcsoft.demo.ai.aicalculator.component.keypad

/**
 * All keys offered by the keypad component.
 *
 * Each key knows its icon resource and the resource bundle key of its description.
 *
 * @property iconName file name of the key icon below `resources/icons`.
 * @property bundleKey key of the description text within the resource bundle.
 */
enum class KeypadKey(val iconName: String, val bundleKey: String) {

    /** Digit key `0`. */
    DIGIT_0("key-0@32.png", "keypad.key.digit0"),

    /** Digit key `1`. */
    DIGIT_1("key-1@32.png", "keypad.key.digit1"),

    /** Digit key `2`. */
    DIGIT_2("key-2@32.png", "keypad.key.digit2"),

    /** Digit key `3`. */
    DIGIT_3("key-3@32.png", "keypad.key.digit3"),

    /** Digit key `4`. */
    DIGIT_4("key-4@32.png", "keypad.key.digit4"),

    /** Digit key `5`. */
    DIGIT_5("key-5@32.png", "keypad.key.digit5"),

    /** Digit key `6`. */
    DIGIT_6("key-6@32.png", "keypad.key.digit6"),

    /** Digit key `7`. */
    DIGIT_7("key-7@32.png", "keypad.key.digit7"),

    /** Digit key `8`. */
    DIGIT_8("key-8@32.png", "keypad.key.digit8"),

    /** Digit key `9`. */
    DIGIT_9("key-9@32.png", "keypad.key.digit9"),

    /** Addition key `+`. */
    ADD("key-add@32.png", "keypad.key.add"),

    /** Subtraction key `-`. */
    SUBTRACT("key-subtract@32.png", "keypad.key.subtract"),

    /** Multiplication key `*`. */
    MULTIPLY("key-multiply@32.png", "keypad.key.multiply"),

    /** Division key `/`. */
    DIVIDE("key-divide@32.png", "keypad.key.divide"),

    /** Result key `=`. */
    EQUALS("key-equals@32.png", "keypad.key.equals"),

    /** Clear key `C`. */
    CLEAR("key-clear@32.png", "keypad.key.clear")
}
