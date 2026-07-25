---
name: ui-window
description: Structure of a UI (JavaFX) stage when it is created or modified
---

# General

- Every stage is to be placed in the sub-package `window`
- Every stage must be implemented as a standalone object, always using the MVVM pattern
  - There MUST ALWAYS be an associated FXML file that describes the stage
    - This MUST ALWAYS be located in the same "package" in the resources folder
    - The FXML MUST NEVER be declared as `fx:root`, but must contain the concrete container
    - The name of the file MUST ALWAYS be `<Name>View.fxml`
  - There MUST ALWAYS be a stage file
    - This ALWAYS derives from the type `Stage`
    - This represents the stage as an object
    - The name MUST ALWAYS be `<Name>`
  - There MUST ALWAYS be a stage view file
    - This is the "code behind" of the FXML file (controller)
    - This MUST ALWAYS contain the interface `Initializable`
    - This MUST ALWAYS bind the view model and the FXML (and its components)
    - The name MUST ALWAYS be `<Name>View` (like the FXML)
    - ALL FXML component injections MUST ALWAYS be annotated with `@FXML`
      - The injected fields/properties MUST ALWAYS be `private`
      - FXML-bound handler functions MUST ALWAYS be `@FXML` and `private`
  - There MUST ALWAYS be a stage ViewModel
    - This ALWAYS contains all necessary bindings and properties needed to process the display (I/O)
    - The name MUST ALWAYS be `<Name>ViewModel`
  - Please adhere to the rules of the MVVM framework to be used (see Rules)

# Final self-check

Before reporting the work as finished, verify each point explicitly and report the result:

- [ ] Stage file, view file, ViewModel and FXML exist and are named correctly
- [ ] The FXML uses a concrete container and an `fx:controller` (NOT `fx:root`)
- [ ] The stage derives from `Stage` and the primary stage is not used
- [ ] Components are embedded as their own component tags, never rebuilt inside the window
- [ ] All public members have KDoc (English)
- [ ] All UI texts come from the resource bundle
- [ ] All FXML injections are `@FXML` and `private`
- [ ] Derived state is expressed as bindings, never as listeners writing properties
