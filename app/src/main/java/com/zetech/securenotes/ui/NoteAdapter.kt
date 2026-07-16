package com.zetech.securenotes.ui
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.zetech.securenotes.databinding.ItemNoteBinding
import com.zetech.securenotes.model.Note
import java.text.DateFormat
import java.util.Date
class NoteAdapter(context:Context):BaseAdapter(){private val inflater=LayoutInflater.from(context);private val items=mutableListOf<Note>();fun submitList(notes:List<Note>){items.clear();items.addAll(notes);notifyDataSetChanged()}override fun getCount()=items.size;override fun getItem(position:Int)=items[position];override fun getItemId(position:Int)=items[position].id;override fun getView(position:Int,convertView:View?,parent:ViewGroup):View{val holder:Holder;val view:View;if(convertView==null){val binding=ItemNoteBinding.inflate(inflater,parent,false);view=binding.root;holder=Holder(binding);view.tag=holder}else{view=convertView;holder=view.tag as Holder};holder.bind(getItem(position));return view}private class Holder(private val b:ItemNoteBinding){fun bind(n:Note){b.noteTitle.text=n.title;b.noteSubtitle.text=n.content;b.noteDate.text=DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.SHORT).format(Date(n.createdAt))}}}
