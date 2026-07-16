# Database schema
## users
- `_id INTEGER PRIMARY KEY AUTOINCREMENT`
- `full_name TEXT NOT NULL`
- `email TEXT NOT NULL UNIQUE COLLATE NOCASE`
- `password_hash TEXT NOT NULL`
- `password_salt TEXT NOT NULL`
- `created_at INTEGER NOT NULL`

## notes
- `_id INTEGER PRIMARY KEY AUTOINCREMENT`
- `user_id INTEGER NOT NULL` → `users._id`
- `title TEXT NOT NULL`
- `content TEXT NOT NULL`
- `created_at INTEGER NOT NULL`
- `ON DELETE CASCADE`; indexed by `user_id`
