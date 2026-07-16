# Oral defense questions and model answers
1. **Why use SQLiteOpenHelper?** It centralizes database creation/versioning through `onCreate()` and `onUpgrade()` and supplies readable/writable database instances.
2. **When does onCreate run?** Once, when the database file is first opened and does not yet exist.
3. **Why use a contract object?** It prevents repeated table/column strings and reduces typing errors.
4. **How are users connected to notes?** `notes.user_id` is a foreign key to `users._id`; every query also filters by the logged-in user ID.
5. **Why is email UNIQUE?** It prevents duplicate accounts; `COLLATE NOCASE` treats differently cased versions as the same email.
6. **Are passwords encrypted?** No. They are one-way hashed using PBKDF2 with a random salt. Verification hashes the attempted password with the stored salt and compares results.
7. **Where is empty-login validation?** `LoginActivity.login()` calls `Validators.emailError()` and `passwordError()` and returns before the executor/database query if either error exists.
8. **Why run database operations on an executor?** Disk I/O can block the UI thread and make the app unresponsive.
9. **What does BaseAdapter do?** It connects the list of `Note` objects to the rows displayed by the `ListView` through count, item, ID, and `getView()` methods.
10. **Why reuse convertView?** Recycling avoids inflating a new XML row for every scroll operation and improves performance.
11. **What does the FAB listener do?** It launches `AddNoteActivity`; the Activity Result callback reloads the list after a successful save.
12. **How is theme saved?** `ThemeManager` writes a string to the `app_settings` SharedPreferences file with `apply()`.
13. **How is theme applied?** The saved string maps to an `AppCompatDelegate` night mode constant; the Application applies it at startup.
14. **Why not save notes in SharedPreferences?** Notes are repeating structured records that need IDs, ownership, ordering, querying, and deletion—SQLite fits this better.
15. **Why separate strings/colors/styles?** It avoids hardcoding, supports localization and day/night alternatives, and makes visual changes maintainable.
16. **Difference between Activity and Fragment here?** Activities host full flows such as login/add/settings; fragments are switchable destinations inside the home Activity.
17. **What happens on logout?** Session preferences are cleared and the login screen starts with NEW_TASK and CLEAR_TASK flags to prevent Back from reopening private notes.
18. **What does ON DELETE CASCADE mean?** Deleting a user automatically deletes notes owned by that user.
19. **What is View Binding?** Build-generated binding classes provide type-safe references to views and reduce `findViewById` errors.
20. **What would you improve for production?** Server-side authentication, encrypted local note content, biometric unlock, repository/ViewModel layers, migrations, and automated tests.
