# Application flow
1. `LoginActivity` is the launcher. `SessionManager` redirects an existing session to `HomeActivity`.
2. Sign-up validates name, email, password, and confirmation before opening SQLite on a background executor.
3. Login checks blank/invalid fields **before** querying SQLite; successful PBKDF2 verification saves only the user ID in session preferences.
4. `HomeActivity` swaps `NotesFragment` and `AccountFragment` through `BottomNavigationView`.
5. `NotesFragment` loads the current user's notes into `NoteAdapter : BaseAdapter`. The FAB opens `AddNoteActivity`.
6. `SettingsActivity` saves `system`, `light`, or `dark` in SharedPreferences and calls `AppCompatDelegate`.
7. Logout clears session preferences and removes previous activities from the back stack.
