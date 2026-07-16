package com.zetech.securenotes.data
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.zetech.securenotes.model.Note
import com.zetech.securenotes.model.User
import com.zetech.securenotes.util.PasswordHasher
class DatabaseHelper(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
 override fun onConfigure(db:SQLiteDatabase){ super.onConfigure(db); db.setForeignKeyConstraintsEnabled(true) }
 override fun onCreate(db:SQLiteDatabase){
  db.execSQL("CREATE TABLE ${DatabaseContract.Users.TABLE} (${DatabaseContract.Users.ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${DatabaseContract.Users.FULL_NAME} TEXT NOT NULL, ${DatabaseContract.Users.EMAIL} TEXT NOT NULL UNIQUE COLLATE NOCASE, ${DatabaseContract.Users.HASH} TEXT NOT NULL, ${DatabaseContract.Users.SALT} TEXT NOT NULL, ${DatabaseContract.Users.CREATED_AT} INTEGER NOT NULL)")
  db.execSQL("CREATE TABLE ${DatabaseContract.Notes.TABLE} (${DatabaseContract.Notes.ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${DatabaseContract.Notes.USER_ID} INTEGER NOT NULL, ${DatabaseContract.Notes.TITLE} TEXT NOT NULL, ${DatabaseContract.Notes.CONTENT} TEXT NOT NULL, ${DatabaseContract.Notes.CREATED_AT} INTEGER NOT NULL, FOREIGN KEY(${DatabaseContract.Notes.USER_ID}) REFERENCES ${DatabaseContract.Users.TABLE}(${DatabaseContract.Users.ID}) ON DELETE CASCADE)")
  db.execSQL("CREATE INDEX index_notes_user ON ${DatabaseContract.Notes.TABLE}(${DatabaseContract.Notes.USER_ID})")
 }
 override fun onUpgrade(db:SQLiteDatabase,oldVersion:Int,newVersion:Int){ db.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.Notes.TABLE}"); db.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.Users.TABLE}"); onCreate(db) }
 fun createUser(fullName:String,email:String,password:String):Long { val salt=PasswordHasher.newSalt(); val v=ContentValues().apply{put(DatabaseContract.Users.FULL_NAME,fullName.trim());put(DatabaseContract.Users.EMAIL,email.trim().lowercase());put(DatabaseContract.Users.HASH,PasswordHasher.hash(password,salt));put(DatabaseContract.Users.SALT,salt);put(DatabaseContract.Users.CREATED_AT,System.currentTimeMillis())}; return writableDatabase.insert(DatabaseContract.Users.TABLE,null,v) }
 fun authenticate(email:String,password:String):User? { readableDatabase.query(DatabaseContract.Users.TABLE,arrayOf(DatabaseContract.Users.ID,DatabaseContract.Users.FULL_NAME,DatabaseContract.Users.EMAIL,DatabaseContract.Users.HASH,DatabaseContract.Users.SALT),"${DatabaseContract.Users.EMAIL} = ?",arrayOf(email.trim().lowercase()),null,null,null,"1").use{c-> if(!c.moveToFirst()) return null; val hash=c.getString(3); val salt=c.getString(4); return if(PasswordHasher.verify(password,salt,hash)) User(c.getLong(0),c.getString(1),c.getString(2)) else null } }
 fun emailExists(email:String):Boolean { readableDatabase.rawQuery("SELECT 1 FROM ${DatabaseContract.Users.TABLE} WHERE ${DatabaseContract.Users.EMAIL}=? LIMIT 1",arrayOf(email.trim().lowercase())).use{return it.moveToFirst()} }
 fun getUser(id:Long):User? { readableDatabase.query(DatabaseContract.Users.TABLE,arrayOf(DatabaseContract.Users.ID,DatabaseContract.Users.FULL_NAME,DatabaseContract.Users.EMAIL),"${DatabaseContract.Users.ID}=?",arrayOf(id.toString()),null,null,null).use{ return if(it.moveToFirst()) User(it.getLong(0),it.getString(1),it.getString(2)) else null } }
 fun addNote(userId:Long,title:String,content:String):Long { val v=ContentValues().apply{put(DatabaseContract.Notes.USER_ID,userId);put(DatabaseContract.Notes.TITLE,title.trim());put(DatabaseContract.Notes.CONTENT,content.trim());put(DatabaseContract.Notes.CREATED_AT,System.currentTimeMillis())}; return writableDatabase.insert(DatabaseContract.Notes.TABLE,null,v) }
 fun getNotes(userId:Long):List<Note> { val list=mutableListOf<Note>(); readableDatabase.query(DatabaseContract.Notes.TABLE,null,"${DatabaseContract.Notes.USER_ID}=?",arrayOf(userId.toString()),null,null,"${DatabaseContract.Notes.CREATED_AT} DESC").use{c->while(c.moveToNext()) list+=Note(c.getLong(c.getColumnIndexOrThrow(DatabaseContract.Notes.ID)),c.getLong(c.getColumnIndexOrThrow(DatabaseContract.Notes.USER_ID)),c.getString(c.getColumnIndexOrThrow(DatabaseContract.Notes.TITLE)),c.getString(c.getColumnIndexOrThrow(DatabaseContract.Notes.CONTENT)),c.getLong(c.getColumnIndexOrThrow(DatabaseContract.Notes.CREATED_AT)))}; return list }
 fun deleteNote(noteId:Long,userId:Long)=writableDatabase.delete(DatabaseContract.Notes.TABLE,"${DatabaseContract.Notes.ID}=? AND ${DatabaseContract.Notes.USER_ID}=?",arrayOf(noteId.toString(),userId.toString()))
 companion object { const val DATABASE_NAME="secure_notes.db"; const val DATABASE_VERSION=1 }
}
