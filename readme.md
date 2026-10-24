# Library Management System - Kotlin Exercise

## Project Overview

This project is a simple Library Management System implemented in Kotlin using Gradle. It was originally provided as a university exercise and is intended to help students understand Kotlin fundamentals, Gradle project structure, and software implementation.

The original project contains intentionally incomplete sections. Students are expected to study the existing code, understand the requirements, and complete the missing functionality.

This repository contains additional automated tests and documentation to evaluate and improve the library management implementation.

## Learning Objectives

By working on this project, students will learn how to:

- Understand the structure of a Kotlin project.
- Use Gradle to build and test an application.
- Work with Kotlin classes, interfaces, data classes, and enums.
- Understand the repository and service layers.
- Implement basic library management operations.
- Read and understand an existing codebase.
- Write unit tests for application functionality.
- Use Git and GitHub for version control and collaboration.
- Identify implementation limitations and propose improvements.

## Prerequisites

Before running the project, ensure that you have the following installed:

- **Java Development Kit (JDK):** A version compatible with the project's Gradle configuration.
- **Git:** To clone and manage the repository.
- **Gradle Wrapper:** Included in the project, so a separate Gradle installation is generally unnecessary.

Useful learning resources:

- [Gradle Documentation](https://docs.gradle.org/)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Kotlin Getting Started](https://kotlinlang.org/docs/getting-started.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

## Project Features

The application is designed to support the following operations:

- Add books to the library.
- Register library patrons.
- Borrow books.
- Return borrowed books.
- Search for books by title or author.
- List all books.
- Display patron information and borrowed books.
- Delete books when the operation is permitted.
- Delete patrons when the operation is permitted.
- Display library statistics.

The project uses an in-memory repository. Therefore, records are not permanently stored after the application exits.

## Project Structure

The main application code is organized into packages under `app/src/main/kotlin`.

```text
app/
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       └── org/
│   │           └── dbu/
│   │               └── library/
│   │                   ├── model/
│   │                   ├── repository/
│   │                   ├── service/
│   │                   └── ui/
│   └── test/
│       └── kotlin/
│           └── org/
│               └── example/
│                   └── LibraryServiceTest.kt
├── build.gradle.kts
└── ...
```

The exact project structure may contain additional files.

### Main Components

- **Model:** Defines library data, including books and patrons.
- **Repository:** Manages the in-memory storage and retrieval of library records.
- **Service:** Implements library business rules, including borrowing, returning, searching, and deletion.
- **UI:** Handles interaction between the user and the library service.
- **Tests:** Verify that important operations work correctly and handle common error cases.

## Original Assignment Instructions

The original exercise asks students to inspect the provided runnable JAR and existing source code, then implement the missing functionality.

The original learning tasks include:

1. Run the provided application JAR, if it is supplied.
2. Inspect the console application and understand its expected behavior.
3. Complete the TODO sections in the source code.
4. Build and run the application.
5. Commit and push the completed work to GitHub.

The original assignment identifies the repository and service implementation as important areas to complete.

## Getting Started

### 1. Clone the Repository

Clone this repository using Command Prompt:

```bat
git clone https://github.com/kura458/kotlin-library-management-improved.git
```

Move into the project directory:

```bat
cd kotlin-library-management-improved
```

### 2. Build the Project

On Windows, run:

```bat
gradlew.bat clean build --console=plain
```

On Linux or macOS, run:

```bash
./gradlew clean build
```

### 3. Run the Application

On Windows:

```bat
gradlew.bat run --console=plain
```

On Linux or macOS:

```bash
./gradlew run
```

### 4. Run Automated Tests

On Windows:

```bat
gradlew.bat clean test --console=plain
```

On Linux or macOS:

```bash
./gradlew clean test
```

A successful test run should finish with a `BUILD SUCCESSFUL` message.

### 5. Create a JAR

On Windows:

```bat
gradlew.bat jar
```

The generated JAR is normally located under:

```text
app/build/libs/
```

### 6. Create a Distribution ZIP

On Windows:

```bat
gradlew.bat distZip
```

The generated distribution ZIP is normally located under:

```text
app/build/distributions/
```

## Additional Improvements and Testing

This repository includes additional automated tests and documentation created during the implementation review.

### Automated Tests Added

The additional `LibraryServiceTest.kt` tests cover the following scenarios:

- Rejecting a book with a duplicate ISBN.
- Successfully borrowing an available book.
- Handling a request for a book that does not exist.
- Handling a request from a patron who does not exist.
- Rejecting a borrowing request when the book is unavailable.
- Successfully returning a borrowed book.
- Searching for books without case sensitivity.
- Enforcing the limit of three borrowed books per patron.

These tests are intended to verify the core library service behavior and help prevent regressions when the implementation changes.

### Test Results

The following command was executed after adding the additional tests:

```bat
gradlew.bat clean test --console=plain
```

**Result:** `BUILD SUCCESSFUL`

This confirms that the project compiled and the automated tests passed in the tested environment at the time of verification.

## Implementation Comparison

The implementation review compares the original project with the reviewed implementation and records observations about functionality, behavior, testing, and possible improvements.

See [`IMPLEMENTATION_COMPARISON.md`](IMPLEMENTATION_COMPARISON.md) for the detailed comparison.

The comparison is intended to help identify:

- Implemented library operations.
- Business rules enforced by the service.
- Existing test coverage.
- Potential edge cases.
- Areas that could benefit from further development.

## Future Enhancements

The following improvements are proposed for future development. They should not be considered implemented unless they are subsequently added and tested.

- Add persistent database storage using SQLite or PostgreSQL.
- Improve input validation for book and patron details.
- Add tests for deletion operations and library statistics.
- Test duplicate patron IDs and other edge cases.
- Ensure repository update operations cannot unintentionally create missing records.
- Make the displayed menu consistent with the operations supported by the menu handler.
- Improve error messages and handling of invalid user input.
- Add data backup and export functionality.
- Improve separation between application logic and user-interface code.

See [`FUTURE_ENHANCEMENTS.md`](FUTURE_ENHANCEMENTS.md) for the proposed enhancement list.

## Current Limitations

- The application uses in-memory storage, so records are lost when the application exits.
- The current additional tests focus on core borrowing, returning, searching, and duplicate-book behavior.
- Further testing is needed for deletion restrictions, library statistics, duplicate patron IDs, and other edge cases.
- Database persistence and other proposed enhancements have not been implemented merely by documenting them.

## Version Control and Submission

To check your changes:

```bat
git status
```

To stage and commit your changes:

```bat
git add .
git commit -m "Add library service tests and documentation"
```

To push your changes to GitHub:

```bat
git push origin main
```

Before submitting, verify that the project builds successfully and that the tests pass.

## Related Repositories

- **Improved review repository:** https://github.com/kura458/kotlin-library-management-improved
- **Reviewed classmate repository:** https://github.com/malfiya1212/kotlin__gradle_project
- **Original university repository:** https://github.com/Debre-birhan-university/kotlin__gradle_project

## Author and Academic Purpose

This project is maintained as part of a university software development exercise involving Kotlin, Gradle, automated testing, code review, documentation, and version control.

The goal is to understand an existing implementation, test its behavior, document observations, and propose meaningful improvements.
