---
description: Everything related to UI programming under JavaFX must observe this rule
---

# Global Rules for JavaFX

- NEVER use the primary stage
  - Every used stage must be a standalone object

# Package Structure

- UI components MUST be located under the sub-package `component`
- UI windows MUST be located under the sub-package `window`

# General

- All UI objects (components, windows) must be implemented as a standalone and embeddable (for components) object

# Presentation

- There is only one window, the main window
- All components on the main window are developed as standalone and embeddable components
- All presentations MUST be depicted as an icon

# Component vs Stage

Whether a component should be created or whether the definition directly in the stage (window) is sufficient MUST ALWAYS be decided by the user.
