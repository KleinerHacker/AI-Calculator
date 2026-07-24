# Global Rules

- All console output of the AI MUST be printed in German
- Within the project the language is English (files)
- NEVER add dependencies unasked: ask the user
- NEVER add your own architecture decisions in code unasked: ask the user
- NEVER add your own packages or package structures outside the ruleset unasked: ask the user

# Programming

- The entire project is written in Kotlin
- Each class should be in its own file
  - An exception is a class that is explicitly intended for the other class it is contained in

## Package Structure

- The root package MUST be `de.pcsoft.demo.ai.aicalculator`
- The main method and application MUST be located at the root level

# Architecture

- JavaFX with the MVVM pattern MUST be used
  - This MUST be realized using the library `de.saxsys:mvvmfx`

# GIT

- For file operations
  - Renaming or moving: git mv MUST ALWAYS be used
  - Deleting: git rm MUST ALWAYS be used
  - Creating: git add MUST ALWAYS be applied afterwards to add the file
- A writing GIT action MUST NEVER be triggered
- The project is hosted on GitHub

# Planning

- A change to the code MUST NEVER take place without prior planning
  - Should a change be required, plan mode MUST be used
  - The only exception is for debugging
- The plan MUST be divided into clear implementation steps that have a short and concise heading
- Below the implementation steps, the plan MUST explain what needs to be changed in individual short and concise bullet points with no more than 20 words
- The plan MUST NOT contain a summary or explanation of what is to be implemented

# Implementation

- Implement changes within a file in a single step
  - NEVER implement them in multiple sub-steps
- Execute each implementation step individually
  - You MUST stop after each implementation step so the user can review the code

# Documentation

- KDocs code documentation MUST exist for ALL public members
- An MKDocs documentation (folder `docs`) MUST exist
  - Description of the calculator itself
  - Description of the UI usage
  - Language: German and English according to i18n
  - All *.MD files MUST be located under `docs/docs`
  - All assets MUST be located under `docs/assets`
- A changelog `CHANGELOG.md` MUST be maintained, which documents all changes under `# [Unreleased]`
  - Only changes that the end user can notice are to be considered
- The README.md file MUST be kept up to date
  - MUST contain
    - What kind of project this is (short description)
    - How to build and run the project on my machine
    - Features
    - Notes on the MKDocs documentation via gh-pages
    - Note on the changelog file

# Tests

- A code coverage of 100% MUST be achieved
- Each test method MUST be provided with a detailed description of the test case in KDocs

# AI

- You MUST NEVER change files under `.claude` on your own, unless the user has allowed it
- You MUST NEVER take project knowledge into your memory
- Project scans should be avoided
