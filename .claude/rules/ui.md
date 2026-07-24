---
description: Everything related to UI programming under JavaFX must observe this rule
---

# Global Rules for JavaFX

- NEVER use the primary stage
  - Every used stage must be a standalone object

# Package Structure

- UI components MUST be located under the sub-package `component`
- UI windows MUST be located under the sub-package `window`

# Resources

- FXML files MUST mirror the package structure of their controller (special case required by the MVVM-FX framework)
- ALL other resources (that are NOT FXML files) MUST NOT be placed into the package structure
  - They MUST be located directly under `resources`, grouped by type
  - Example: icons MUST be located under `resources/icons`

# General

- All UI objects (components, windows) must be implemented as a standalone and embeddable (for components) object

# Presentation

- There is only one window, the main window
- All components on the main window are developed as standalone and embeddable components
- All presentations MUST be depicted as an icon

# Texts / i18n

- ALL texts shown in the UI MUST be defined via a resource bundle (never hard-coded)
- The following languages MUST be supported:
  - English as the default (fallback)
  - German

# Component vs Stage

Whether a component should be created or whether the definition directly in the stage (window) is sufficient MUST ALWAYS be decided by the user.
