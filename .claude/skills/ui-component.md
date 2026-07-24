---
name: ui-component
description: Structure of a UI (JavaFX) component when it is created or modified
---

# General

- Every component is to be placed in the sub-package `component`
- Every component must be implemented as a standalone object, always using the MVVM pattern
  - There MUST ALWAYS be an associated FXML file that describes the component
    - This MUST ALWAYS be located in the same "package" in the resources folder
    - The FXML MUST ALWAYS be declared as `fx:root`
    - The name of the file MUST ALWAYS be `<Name>View.fxml`
  - There MUST ALWAYS be a component file
    - This ALWAYS derives from the type of the referenced `fx:root` in the FXML
    - This represents the embeddable component as an object
    - The name MUST ALWAYS be `<Name>`
  - There MUST ALWAYS be a component view file
    - This is the "code behind" of the FXML file (controller)
    - This MUST ALWAYS contain the interface `Initializable`
    - This MUST ALWAYS bind the view model and the FXML (and its components)
    - The name MUST ALWAYS be `<Name>View` (like the FXML)
  - There MUST ALWAYS be a component ViewModel
    - This ALWAYS contains all necessary bindings and properties needed to process the display (I/O)
    - The name MUST ALWAYS be `<Name>ViewModel`
  - Please adhere to the rules of the MVVM framework to be used (see Rules)
