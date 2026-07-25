---
name: ui-component
description: Structure of a UI (JavaFX) component when it is created or modified
---

# Mandatory files

A component named `<Name>` consists of EXACTLY these four files. A component with a missing file
is a rule violation, even if it works:

| File | Location | Purpose |
| --- | --- | --- |
| `<Name>.kt` | `component/<name>/` | the pure, embeddable component object |
| `<Name>View.kt` | `component/<name>/` | the code behind (controller) of the FXML |
| `<Name>ViewModel.kt` | `component/<name>/` | the state and bindings |
| `<Name>View.fxml` | `resources/.../component/<name>/` | the user interface |

# General

- Every component is to be placed in the sub-package `component`
- Every component must be implemented as a standalone object, always using the MVVM pattern
  - There MUST ALWAYS be an associated FXML file that describes the component
    - This MUST ALWAYS be located in the same "package" in the resources folder
    - The FXML MUST ALWAYS be declared as `fx:root`
      - CORRECT: `<fx:root type="javafx.scene.layout.VBox" styleClass="...">`
      - WRONG: a concrete container tag (`<VBox>`) and/or an `fx:controller` attribute
      - An `fx:controller` attribute MUST NEVER be present: the code behind is passed via `codeBehind(...)`
    - The name of the file MUST ALWAYS be `<Name>View.fxml`
  - There MUST ALWAYS be a component file
    - This ALWAYS derives from the type of the referenced `fx:root` in the FXML
    - This represents the embeddable component as an object
    - The name MUST ALWAYS be `<Name>`
    - It MUST ALWAYS provide a no-argument constructor, so it can be embedded from FXML
    - It MUST ALWAYS load itself in its `init` block:
      `FluentViewLoader.fxmlView(<Name>View::class.java).root(this).codeBehind(<Name>View()).resourceBundle(...).load()`
    - It MUST ALWAYS expose its OWN public API (properties and functions) for the outside world
      - The ViewModel MUST ALWAYS stay `private` and MUST NEVER be published
      - The API delegates to the ViewModel
  - There MUST ALWAYS be a component view file
    - This is the "code behind" of the FXML file (controller)
    - This MUST ALWAYS contain the interface `Initializable`
    - This MUST ALWAYS bind the view model and the FXML (and its components)
    - The name MUST ALWAYS be `<Name>View` (like the FXML)
    - ALL FXML component injections MUST ALWAYS be annotated with `@FXML`
      - The injected fields/properties MUST ALWAYS be `private`
      - FXML-bound handler functions MUST ALWAYS be `@FXML` and `private`
  - There MUST ALWAYS be a component ViewModel
    - This ALWAYS contains all necessary bindings and properties needed to process the display (I/O)
    - The name MUST ALWAYS be `<Name>ViewModel`
  - Please adhere to the rules of the MVVM framework to be used (see Rules)

# Final self-check

Before reporting the work as finished, verify each point explicitly and report the result:

- [ ] All four mandatory files exist for the component
- [ ] The FXML starts with `<fx:root type="...">` and contains NO `fx:controller`
- [ ] The component class derives from exactly the type stated in `fx:root`
- [ ] The component class loads itself via `.root(this).codeBehind(...)`
- [ ] The ViewModel is `private` in the component class; only the own API is public
- [ ] All public members have KDoc (English)
- [ ] All UI texts come from the resource bundle
- [ ] All FXML injections are `@FXML` and `private`
- [ ] Derived state is expressed as bindings, never as listeners writing properties
