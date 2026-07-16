package com.zetech.securenotes.data
import android.provider.BaseColumns
object DatabaseContract {
 object Users: BaseColumns { const val TABLE="users"; const val ID="_id"; const val FULL_NAME="full_name"; const val EMAIL="email"; const val HASH="password_hash"; const val SALT="password_salt"; const val CREATED_AT="created_at" }
 object Notes: BaseColumns { const val TABLE="notes"; const val ID="_id"; const val USER_ID="user_id"; const val TITLE="title"; const val CONTENT="content"; const val CREATED_AT="created_at" }
}
