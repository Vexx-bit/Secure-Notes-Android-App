package com.zetech.securenotes.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.zetech.securenotes.R
import com.zetech.securenotes.data.DatabaseHelper
import com.zetech.securenotes.databinding.ActivityAddNoteBinding
import com.zetech.securenotes.util.SessionManager
import java.util.concurrent.Executors
class AddNoteActivity:AppCompatActivity(){private lateinit var b:ActivityAddNoteBinding;private val executor=Executors.newSingleThreadExecutor();override fun onCreate(s:Bundle?){super.onCreate(s);b=ActivityAddNoteBinding.inflate(layoutInflater);setContentView(b.root);b.backButton.setOnClickListener{finish()};b.saveButton.setOnClickListener{save()}}
 private fun save(){val title=b.titleInput.text?.toString().orEmpty();val content=b.contentInput.text?.toString().orEmpty();b.titleLayout.error=if(title.isBlank())getString(R.string.title_required) else null;b.contentLayout.error=if(content.isBlank())getString(R.string.content_required) else null;if(b.titleLayout.error!=null||b.contentLayout.error!=null)return;b.saveButton.isEnabled=false;executor.execute{val id=DatabaseHelper(this).addNote(SessionManager(this).userId(),title,content);runOnUiThread{if(id>0){setResult(RESULT_OK);finish()}else{b.saveButton.isEnabled=true;Snackbar.make(b.root,R.string.note_save_failed,Snackbar.LENGTH_LONG).show()}}}}override fun onDestroy(){executor.shutdown();super.onDestroy()}}
