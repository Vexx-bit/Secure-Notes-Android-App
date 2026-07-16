package com.zetech.securenotes.ui
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.zetech.securenotes.R
import com.zetech.securenotes.data.DatabaseHelper
import com.zetech.securenotes.databinding.FragmentNotesBinding
import com.zetech.securenotes.util.SessionManager
import java.util.concurrent.Executors
class NotesFragment:Fragment(){private var _b:FragmentNotesBinding?=null;private val b get()=_b!!;private val executor=Executors.newSingleThreadExecutor();private lateinit var adapter:NoteAdapter;private val launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){if(it.resultCode==Activity.RESULT_OK)loadNotes()};override fun onCreateView(i:LayoutInflater,c:ViewGroup?,s:Bundle?):View{_b=FragmentNotesBinding.inflate(i,c,false);return b.root}override fun onViewCreated(v:View,s:Bundle?){adapter=NoteAdapter(requireContext());b.notesList.adapter=adapter;b.addNoteFab.setOnClickListener{launcher.launch(Intent(requireContext(),AddNoteActivity::class.java))};b.notesList.setOnItemLongClickListener{_,_,position,_->val note=adapter.getItem(position);AlertDialog.Builder(requireContext()).setTitle(R.string.delete_note).setMessage(R.string.delete_note_question).setNegativeButton(R.string.cancel,null).setPositiveButton(R.string.delete){_,_->executor.execute{DatabaseHelper(requireContext()).deleteNote(note.id,SessionManager(requireContext()).userId());activity?.runOnUiThread{loadNotes()}}}.show();true};loadNotes()}private fun loadNotes(){val context=context?:return;executor.execute{val notes=DatabaseHelper(context).getNotes(SessionManager(context).userId());activity?.runOnUiThread{if(_b==null)return@runOnUiThread;adapter.submitList(notes);b.emptyState.visibility=if(notes.isEmpty())View.VISIBLE else View.GONE}}}override fun onDestroyView(){_b=null;super.onDestroyView()}override fun onDestroy(){executor.shutdown();super.onDestroy()}}
