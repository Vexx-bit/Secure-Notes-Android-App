package com.zetech.securenotes.util
import android.content.Context
class SessionManager(context:Context){ private val prefs=context.getSharedPreferences("session_preferences",Context.MODE_PRIVATE); fun saveUser(id:Long)=prefs.edit().putLong(KEY_USER_ID,id).apply(); fun userId()=prefs.getLong(KEY_USER_ID,-1L); fun isLoggedIn()=userId()!=-1L; fun clear()=prefs.edit().clear().apply(); companion object{private const val KEY_USER_ID="logged_in_user_id"} }
