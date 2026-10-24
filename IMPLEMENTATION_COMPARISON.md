\# Implementation Comparison: Kotlin Library Management System



\## 1. Introduction



This document compares the library management implementation completed in my original university project with the implementation reviewed from another student's repository.



\*\*Original project:\*\* \[kura458/kotlin\_\_gradle\_project](https://github.com/kura458/kotlin\_\_gradle\_project)



\*\*Reviewed project:\*\* \[malfiya1212/kotlin\_\_gradle\_project](https://github.com/malfiya1212/kotlin\_\_gradle\_project)



\*\*Improved working repository:\*\* \[kura458/kotlin-library-management-improved](https://github.com/kura458/kotlin-library-management-improved)



The purpose of this comparison is to identify shared functionality, additional features, testing gaps, and opportunities for future improvement.



\## 2. Feature Comparison



| Feature | Original Implementation | Reviewed Implementation |

|---|---|---|

| In-memory book storage | Supported | Supported |

| In-memory patron storage | Supported | Supported |

| Duplicate ISBN prevention | Supported | Supported |

| Duplicate patron ID prevention | Supported | Supported |

| Borrowing and returning books | Supported | Supported |

| Maximum of three borrowed books | Supported | Supported |

| Case-insensitive title and author search | Supported | Supported |

| Delete books | Not included in the implementation reviewed | Supported, with a check preventing deletion of borrowed books |

| Delete patrons | Not included in the implementation reviewed | Supported, with a check preventing deletion of patrons who have borrowed books |

| Library statistics | Not included in the implementation reviewed | Supported |

| Patron contact information | Basic patron information | Includes email and phone |

| Automated library service tests | Tested separately during the improvement work | Initially had only a basic application greeting test; additional service tests have now been added in the improved working repository |



\## 3. Strengths of the Reviewed Implementation



The reviewed implementation provides several useful features:



\- It separates models, repository operations, business logic, and console user-interface code.

\- It uses `BorrowResult` values to describe borrowing outcomes, including missing books, missing patrons, unavailable books, and borrowing-limit violations.

\- It prevents deletion of books that are currently borrowed.

\- It prevents deletion of patrons who still have borrowed books.

\- It provides statistics for total books, available books, borrowed books, and registered patrons.

\- It supports case-insensitive searches by book title or author.



\## 4. Identified Improvement Opportunities



The initial test suite only checked whether the application greeting was not null. It did not directly verify the main library operations.



To address this testing gap, additional service tests were created for:



\- Rejecting duplicate ISBNs.

\- Successfully borrowing an available book.

\- Handling missing books and patrons.

\- Rejecting a second borrowing of an unavailable book.

\- Successfully returning a borrowed book.

\- Searching without case sensitivity.

\- Enforcing the three-book borrowing limit.



The command `gradlew.bat clean test --console=plain` completed successfully after these tests were added.



Further improvements could include tests for duplicate patron IDs, invalid returns, deletion restrictions, statistics, and repository update behavior.



\## 5. Conclusion



Both implementations provide the core functionality of an in-memory library management system. The reviewed implementation extends the functionality with deletion operations, patron contact details, and library statistics.



The main improvement made during this review was strengthening automated testing of the library service. These tests provide better evidence that important business rules work as expected and establish a foundation for future enhancements.



