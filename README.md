# AI-Calculator

A small demo desktop calculator built with Kotlin and JavaFX (MVVM via mvvmfx).
This repository currently contains the prepared application base: an empty main window
with an application icon derived from the project logo.

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

## Documentation

User documentation is written with [MkDocs](https://www.mkdocs.org/) and lives under
`docs/`. The Markdown pages are located in `docs/docs`, assets in `docs/docs/assets`.
The documentation is intended to be published to GitHub Pages (`gh-pages` branch).

## Changelog

All user-visible changes are tracked in [CHANGELOG.md](CHANGELOG.md) under `# [Unreleased]`.
