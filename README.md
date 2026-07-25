# AI-Calculator

A small demo desktop calculator built with Kotlin and JavaFX (MVVM via mvvmfx).
This repository currently contains the application base: a fixed-size main window (300 x 500)
with the formula/result display, the calculator keypad and an application icon derived from the
project logo. The keys are connected to the display: every key press extends the formula.

## Build & Run

Prerequisites: JDK 21.

```bash
# Build and run the tests
./gradlew build

# Start the application
./gradlew run
```

On Windows you can use `gradlew.bat` instead of `./gradlew`.

## Features

- Standalone main window (JavaFX `Stage`) following the MVVM pattern
- Application/window icon derived from the project logo (16/24/32/48/64 px)
- Internationalized window title (German/English)
- Embeddable keypad component with digits, operators, result and clear keys (icon-only)
- Full keyboard operation of the keypad including tooltips and accessible texts (German/English)
- Embeddable display component showing the current formula in small text and the permanently
  calculated result in large text, with tooltips for truncated content
- Keypad and display connected: a key press hands the display its next character, the display
  owns and extends the formula itself
- Several operators or decimal points entered in a row are reduced to the last entered character
- Calculation of the four basic arithmetic operations on arbitrary numbers (`BigDecimal`),
  tolerant towards incomplete formulas

## Documentation

User documentation is written with [MkDocs](https://www.mkdocs.org/) and lives under
`docs/`. The Markdown pages are located in `docs/docs`, assets in `docs/docs/assets`.
The documentation is intended to be published to GitHub Pages (`gh-pages` branch).

## Changelog

All user-visible changes are tracked in [CHANGELOG.md](CHANGELOG.md) under `# [Unreleased]`.
