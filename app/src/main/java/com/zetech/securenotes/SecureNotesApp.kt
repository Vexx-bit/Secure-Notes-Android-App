package com.zetech.securenotes
import android.app.Application
import com.zetech.securenotes.util.ThemeManager
class SecureNotesApp : Application() { override fun onCreate() { super.onCreate(); ThemeManager.applySavedTheme(this) } }
