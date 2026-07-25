# Using the UI

Start the application via `./gradlew run`. The main window opens as a standalone
window and shows the application icon in the title bar and task bar. The window has a
fixed size of 300 x 500 pixels and cannot be resized.

## Formula and result display

The upper area of the window shows the display:

- the current formula in small text on the first line
- the current result in large text below it

Both are shown at all times. As long as no formula has been entered, the formula line shows an
example formula (`12+34*2`) as a hint and the result line shows `0`. A formula that cannot
be evaluated keeps being displayed unchanged, the result then falls back to `0`.

The display never grows beyond the window. If a formula or result is too long for the
available width, it is truncated at the beginning and marked with an ellipsis; the complete
text is then shown as a tooltip when hovering the line.

The display owns the formula: every key press hands it the next character, which it appends to
the formula on its own.

## Entering a formula

Pressing a key appends its character to the formula, and formula and result are updated
immediately. Two entries are corrected while typing:

- if several operators (`+`, `-`, `*`, `/`) are entered in a row, only the last one counts:
  `1+` followed by `*` becomes `1*`
- if several decimal points are entered in a row, only the last one counts

The `C` key clears the formula completely, the `=` key recalculates the result of the current
formula. Since the result is displayed at all times anyway, `=` is only needed to force a
recalculation.

## Keypad

The centre of the window contains the keypad. It is arranged like a classic pocket
calculator:

| Row | Keys |
| --- | --- |
| 1 | `C` `/` `*` `-` |
| 2 | `7` `8` `9` `+` (double height) |
| 3 | `4` `5` `6` |
| 4 | `1` `2` `3` `=` (double height) |
| 5 | `0` (triple width) |

Every key is labelled with an icon only. Hovering a key shows a tooltip containing the
symbol, its meaning and the keyboard shortcut; the same text is provided to screen readers.

## Keyboard shortcuts

| Key | Shortcut |
| --- | --- |
| `0` - `9` | digits, numeric keypad included |
| `+` `-` `*` `/` | the corresponding character |
| `=` | `Enter` or `=` |
| `C` | `Esc` or `Del` |
