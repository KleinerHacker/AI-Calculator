---
name: test-ui
description: Create new tests for UI (JavaFX)
---

# General

- Tests must be located in the same folder as the class under test
- Tests must be executed using TestFX (framework)
  - The execution must be designed so that no window has to pop up
    - The test must also work headless on a Linux pipeline (CI/CD capable)
- The tests should check all available and non-static I/O sub-components of the component or the window
