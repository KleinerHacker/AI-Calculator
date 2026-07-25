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

# MVVM Binding

- The connection between View and ViewModel MUST ALWAYS be realized via JavaFX bindings
  - The View MUST only bind control properties to ViewModel properties
  - The View MUST NEVER read or write ViewModel values imperatively (no `get`/`set` on state)
- Every value a ViewModel derives from other state MUST be expressed as a binding
  - Use `Bindings.*`, the fluent API or a custom `Binding` implementation
  - Change listeners that imperatively write into another property are NOT allowed
  - `set(...)` on a derived property is NOT allowed: derived properties are bound, never assigned
- Bindings MUST be created in the Kotlin property initializer or in the constructor
  - NEVER in an `initialize()` method or any other later hook
  - Injected members (e.g. the resource bundle) may be read inside the binding lambda,
    because bindings evaluate lazily
- A ViewModel MUST expose its state as plain Kotlin properties, not as getter/setter methods
  - State provided from outside: a writable property (e.g. `StringProperty`)
  - Derived state: the binding itself (e.g. `StringBinding`), which is read-only by nature
- A listener is only permissible for side effects that are not a value derivation
  - Example: triggering an action, logging
  - It MUST NEVER be used to keep two properties in sync: use a binding instead

# Texts / i18n

- ALL texts shown in the UI MUST be defined via a resource bundle (never hard-coded)
  - Language neutral placeholder texts (e.g. an example formula, `0`) do NOT need a translation
    and are defined in the default bundle only
- The following languages MUST be supported:
  - English as the default (fallback)
  - German

# Component vs Stage

Whether a component should be created or whether the definition directly in the stage (window) is sufficient MUST ALWAYS be decided by the user.
