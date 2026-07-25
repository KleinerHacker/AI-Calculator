# Changelog

All notable, user-visible changes are documented here.

## [Unreleased]

### Added
- Application and window icon derived from the project logo (16/24/32/48/64 px)
- German/English window title (i18n)
- Keypad in the main window with digits 0-9, the operators `+`, `-`, `*`, `/`, the result key `=`
  and the clear key `C` (icon-only keys)
- Keypad and display are connected: every key press appends its character to the formula, and
  formula and result are updated immediately
- Entering several operators in a row keeps only the last entered operator (`1+` plus `*`
  becomes `1*`), the same applies to several decimal points in a row
- The `C` key clears the formula, the `=` key recalculates the result of the current formula
- Tooltips and accessible texts for all keys in German and English
- Keyboard operation of all keys (digits incl. numeric keypad, operators, `Enter`/`=`, `Esc`/`Del`)
- Visual design based on the application logo: bright blue/orange colour scheme, rounded keys,
  hover and pressed feedback and colour-coded key groups (digits, operators, result, clear)
- Display above the keypad showing the current formula in small text and the permanently
  calculated result in large text (both are always visible, even for a corrupt formula);
  without any input the formula line shows an example formula and the result line `0`
- Formula and result too long for the available width are truncated at the beginning and can be
  read in full via tooltip
- Fixed main window size of 300 x 500 pixels; the window can no longer be resized
