\# Future Enhancements — Library Management System



\## 1. Introduction



This document describes proposed improvements to the Kotlin Library Management System. The enhancements aim to improve reliability, data management, testing, usability, and maintainability.



These are proposals for future development, not claims that the features have already been implemented.



\## 2. Proposed Enhancements



\### 2.1 Persistent Database Storage



\*\*Current limitation:\*\* The application uses in-memory storage, so records are lost when the application exits.



\*\*Proposed improvement:\*\* Integrate a database such as SQLite or PostgreSQL to store books, patrons, and borrowing records permanently.



\*\*Expected benefit:\*\* Library data can be retained between application sessions.



\### 2.2 Improved Input Validation



\*\*Current limitation:\*\* Invalid or incomplete book and patron information may require additional validation.



\*\*Proposed improvement:\*\* Validate ISBNs, names, email addresses, and other required fields before saving records.



\*\*Expected benefit:\*\* More consistent and reliable data.



\### 2.3 Expanded Automated Testing



\*\*Current limitation:\*\* The additional tests currently cover important borrowing, returning, searching, and duplicate-book scenarios.



\*\*Proposed improvement:\*\* Add tests for:



\- Duplicate patron IDs.

\- Returning a book that was not borrowed by the specified patron.

\- Returning a book that is already available.

\- Deleting a borrowed book.

\- Deleting a patron who has borrowed books.

\- Library statistics.

\- Repository update operations.

\- Empty and invalid search queries.



\*\*Expected benefit:\*\* Better coverage of normal behavior, error handling, and edge cases.



\### 2.4 Safer Repository Updates



\*\*Potential issue:\*\* Repository update methods should distinguish between updating an existing record and creating a new record.



\*\*Proposed improvement:\*\* Make update operations fail or return an appropriate result when the specified book or patron does not exist.



\*\*Expected benefit:\*\* Reduced risk of unintended records being created during updates.



\### 2.5 Consistent Menu Options



\*\*Potential issue:\*\* The displayed menu and the operations supported by the menu handler should remain consistent.



\*\*Proposed improvement:\*\* Review the menu and its handler together, ensuring every displayed option has the correct behavior and every supported operation is clearly accessible.



\*\*Expected benefit:\*\* A more predictable user experience.



\### 2.6 Better Error Handling



\*\*Proposed improvement:\*\* Provide clear messages for invalid input, missing records, unavailable books, and borrowing-limit violations.



\*\*Expected benefit:\*\* Users can understand why an operation failed and how to correct the problem.



\### 2.7 Data Export and Backup



\*\*Proposed improvement:\*\* Add the ability to export library records to a CSV file and, where persistent storage is introduced, implement suitable backup and recovery procedures.



\*\*Expected benefit:\*\* Easier reporting and improved protection against data loss.



\### 2.8 Improved Separation of Responsibilities



\*\*Proposed improvement:\*\* Keep business rules in the service layer, storage operations in the repository layer, and user interaction in the UI layer.



\*\*Expected benefit:\*\* Easier maintenance, testing, and future development.



\## 3. Suggested Implementation Order



1\. Expand automated tests for existing behavior.

2\. Improve validation and error handling.

3\. Review repository update semantics.

4\. Ensure menu options match handler functionality.

5\. Introduce persistent database storage.

6\. Add export and backup capabilities.



\## 4. Conclusion



These enhancements provide a roadmap for improving the Library Management System. They should be implemented incrementally, with automated tests run after each meaningful change to ensure existing functionality continues to work correctly.



