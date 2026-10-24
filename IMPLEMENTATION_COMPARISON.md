# Implementation Comparison: Kotlin Library Management System

## 1. Introduction

This document compares my independently completed Kotlin Library Management System with a previous student's implementation. The purpose is to understand the design and implementation approaches, identify strengths and weaknesses, and make meaningful improvements.

### Project Repositories

- **My independent implementation:** [kura458/kotlin__gradle_project](https://github.com/kura458/kotlin__gradle_project)
- **Selected student's implementation:** [malfiya1212/kotlin__gradle_project](https://github.com/malfiya1212/kotlin__gradle_project)
- **Improved review repository:** [kura458/kotlin-library-management-improved](https://github.com/kura458/kotlin-library-management-improved)

The improved review repository contains additional automated tests and documentation based on the reviewed implementation.

## 2. Project Structure and Design

Both projects use Kotlin and Gradle and organize library functionality into components such as models, repositories, services, and user-interface code.

The reviewed implementation contains the following important components:

| Component | Responsibility |
|---|---|
| `Book.kt` | Represents a book using ISBN, title, author, year, and availability. |
| `Patron.kt` | Represents a library patron and their borrowed-book ISBNs. |
| `LibraryService.kt` | Defines operations that the library service must support. |
| `BorrowResult.kt` | Represents possible outcomes of a book-borrowing operation. |
| `InMemoryLibraryRepository.kt` | Manages in-memory book and patron records. |
| `DefaultLibraryService.kt` | Implements library business rules. |
| `MenuHandler.kt` | Handles console operations and user interactions. |

My independent implementation also includes repository, service, and UI components, including `InMemoryLibraryRepository.kt`, `DefaultLibraryService.kt`, and `MenuHandler.kt`.

The reviewed implementation makes the borrowing result explicit through an enum. For example:

```kotlin
enum class BorrowResult {
    SUCCESS,
    BOOK_NOT_FOUND,
    PATRON_NOT_FOUND,
    NOT_AVAILABLE,
    LIMIT_REACHED
}
```

This approach makes borrowing outcomes easier for the calling code to distinguish than a single generic success or failure value.

## 3. Feature Comparison

| Feature | Independent implementation | Reviewed implementation |
|---|---|---|
| In-memory book storage | Implemented | Implemented |
| In-memory patron storage | Implemented | Implemented |
| Adding books | Implemented | Implemented |
| Borrowing and returning books | Implemented | Implemented |
| Borrowing-limit enforcement | Core library functionality implemented | Limit of three books per patron |
| Case-insensitive book search | Core library functionality implemented | Searches titles and authors without case sensitivity |
| Duplicate ISBN handling | Implemented | Rejects duplicate ISBNs |
| Book deletion | Requires direct code verification for a detailed comparison | Prevents deleting borrowed books |
| Patron deletion | Requires direct code verification for a detailed comparison | Prevents deleting patrons with borrowed books |
| Library statistics | Requires direct code verification for a detailed comparison | Reports total, available, and borrowed books, and total patrons |
| Automated service tests | Tested during the independent implementation workflow | Additional service tests were added to the improved review repository |

This comparison distinguishes confirmed features from areas that require further direct inspection. A feature should not be described as missing from the independent implementation unless the source code has been checked.

## 4. Differences in Implementation Approach

### 4.1 Explicit borrowing results

The reviewed implementation uses `BorrowResult` to communicate the outcome of a borrowing operation.

For example, the service can distinguish a missing book from a missing patron:

```kotlin
BorrowResult.BOOK_NOT_FOUND
BorrowResult.PATRON_NOT_FOUND
```

This helps the UI provide more informative messages and avoids treating different failure cases as identical.

### 4.2 Business rules in the service layer

The reviewed `DefaultLibraryService` checks whether the book and patron exist, whether the book is available, and whether the patron has reached the borrowing limit.

A simplified illustration of the borrowing checks is:

```kotlin
if (book == null) {
    return BorrowResult.BOOK_NOT_FOUND
}

if (patron == null) {
    return BorrowResult.PATRON_NOT_FOUND
}

if (!book.isAvailable) {
    return BorrowResult.NOT_AVAILABLE
}
```

This illustrates the importance of validating the requested operation before updating library records. The example is illustrative and is not intended to replace the complete implementation.

### 4.3 Model design

The reviewed `Book.kt` defines a Kotlin data class:

```kotlin
data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val year: Int,
    val isAvailable: Boolean = true
)
```

The data class provides a concise representation of a book and includes a default availability value.

The reviewed `Patron.kt` also records borrowed book ISBNs:

```kotlin
data class Patron(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val borrowedBooks: List<String> = emptyList()
)
```

This design allows the service to track which books belong to each patron's current borrowing list.

## 5. Strengths and Weaknesses

### 5.1 Strengths of the reviewed implementation

- **Clear separation of responsibilities:** Models, repositories, services, and UI code have different roles.
- **Explicit borrowing outcomes:** `BorrowResult` identifies common success and failure cases.
- **Borrowing rules:** The service checks availability and the maximum borrowing limit.
- **Deletion restrictions:** Borrowed books and patrons with outstanding borrowed books cannot be deleted.
- **Library statistics:** The service provides useful information about the library's collection and patrons.
- **Search functionality:** Books can be found by title or author without case-sensitive matching.

### 5.2 Limitations and weaknesses

- **In-memory storage:** Records are not permanently saved when the application exits.
- **Limited initial automated testing:** The existing `AppTest.kt` initially checked the application greeting rather than the main library operations.
- **Additional edge cases need testing:** Deletion restrictions, statistics, duplicate patron IDs, and invalid return operations need more comprehensive automated verification.
- **Repository update semantics:** Update operations should be checked to ensure they do not unintentionally create records when the target record is missing.
- **User-interface consistency:** The displayed menu and the menu handler should be checked together to ensure the available options are consistent.

These points identify areas for further improvement without assuming that every potential issue has already caused an actual failure.

## 6. Improvements Implemented

The main improvement made in the review repository was the addition of automated tests for library service behavior.

The new test file is:

`app/src/test/kotlin/org/example/LibraryServiceTest.kt`

The tests cover:

1. Rejecting duplicate ISBNs.
2. Successfully borrowing an available book.
3. Handling requests for nonexistent books.
4. Handling requests from nonexistent patrons.
5. Rejecting attempts to borrow an unavailable book.
6. Returning a borrowed book successfully.
7. Searching for books without case sensitivity.
8. Enforcing the three-book borrowing limit.

The tests were run using the following Windows Command Prompt command:

```bat
gradlew.bat clean test --console=plain
```

The command completed with:

```text
BUILD SUCCESSFUL
```

This result confirms that the project built and the available automated tests passed in the tested environment.

### Benefits of the improvements

- **More reliable behavior:** Tests verify important borrowing and returning scenarios.
- **Earlier defect detection:** Changes that break tested behavior can be detected during a test run.
- **Easier maintenance:** Future developers can use the tests to check whether existing functionality still works after changes.
- **Clearer documentation:** The comparison and future-enhancement documents explain the current work and identify further development opportunities.

## 7. Why These Improvements Were Chosen

Automated testing was selected as the first improvement because the initial test suite did not directly verify the main library service operations.

Borrowing and returning books involve several related rules: the book must exist, the patron must exist, the book must be available, and the patron must not exceed the borrowing limit. A defect in these rules could lead to inconsistent library records.

The additional tests focus on these essential behaviors before attempting larger changes such as introducing a database or redesigning the application.

This approach provides a practical foundation for future development while keeping the improvement focused and verifiable.

## 8. Conclusion

Both implementations aim to provide the core functionality of an in-memory library management system. The reviewed implementation includes explicit borrowing results, patron contact information, deletion restrictions, and library statistics.

The principal improvement made in the review repository was expanding automated testing to cover important library service operations. The additional tests passed successfully, providing evidence that the tested behaviors work as expected in the current implementation.

Future work should expand edge-case coverage, review repository update behavior, improve input validation, and introduce persistent storage if required.

The purpose of this exercise is to learn from another implementation, evaluate its design, document the evidence, and make improvements that can be tested and explained.

## 9. References

- [Original university repository](https://github.com/Debre-birhan-university/kotlin__gradle_project)
- [Selected student's repository](https://github.com/malfiya1212/kotlin__gradle_project)
- [Independent implementation](https://github.com/kura458/kotlin__gradle_project)
- [Improved review repository](https://github.com/kura458/kotlin-library-management-improved)
