package com.zetech.securenotes.ui
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zetech.securenotes.data.DatabaseHelper
import com.zetech.securenotes.databinding.FragmentAccountBinding
import com.zetech.securenotes.util.SessionManager
import java.util.concurrent.Executors
class AccountFragment:Fragment(){private var _b:FragmentAccountBinding?=null;private val b get()=_b!!;private val executor=Executors.newSingleThreadExecutor();override fun onCreateView(i:LayoutInflater,c:ViewGroup?,s:Bundle?):View{_b=FragmentAccountBinding.inflate(i,c,false);return b.root}override fun onViewCreated(v:View,s:Bundle?){b.settingsButton.setOnClickListener{startActivity(Intent(requireContext(),SettingsActivity::class.java))};b.logoutButton.setOnClickListener{SessionManager(requireContext()).clear();startActivity(Intent(requireContext(),LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))};executor.execute{val user=DatabaseHelper(requireContext()).getUser(SessionManager(requireContext()).userId());activity?.runOnUiThread{if(user!=null&&_b!=null){b.userName.text=user.fullName;b.userEmail.text=user.email}}}}override fun onDestroyView(){_b=null;super.onDestroyView()}override fun onDestroy(){executor.shutdown();super.onDestroy()}}
