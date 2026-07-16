# Secure Notes and Settings — CAT 3
Kotlin/Android Views project built from the **Empty Views Activity** approach. It uses raw `SQLiteOpenHelper`, a custom `ListView` adapter, a bottom navigation bar, and `SharedPreferences` theme settings.

## Open and run
1. Extract the ZIP and open the `SecureNotesCAT3` folder in Android Studio.
2. Allow Gradle Sync to finish. Use JDK 17 and install Android SDK 35 if prompted.
3. Run on an emulator/device with Android 7.0 (API 24) or newer.

## Demonstration order
Sign up → reject empty login → log in → add two notes → show title/subtitle ListView → long-press delete → switch bottom navigation → open Settings → change dark/light/system theme → log out.

## Security note
The assignment requires SQLite credentials. This project stores email plus a **salted PBKDF2-HMAC-SHA256 password hash**, never the password itself. Data remains in Android's app-private storage. This is suitable for demonstrating secure local storage, but production authentication normally uses a trusted server.

See `docs/` for the report, diagrams, oral questions, test checklist, and file architecture.

## Team Review Report
The project report for team review can be found here: [Google Docs Report](https://docs.google.com/document/d/1teOkblZfYpllcWpx5fGDgbb34OaDwybxd7IfLxTpZRg/edit?usp=sharing)

