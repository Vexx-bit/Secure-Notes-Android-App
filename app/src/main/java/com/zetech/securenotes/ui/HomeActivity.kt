package com.zetech.securenotes.ui
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zetech.securenotes.R
import com.zetech.securenotes.databinding.ActivityHomeBinding
import com.zetech.securenotes.util.SessionManager
class HomeActivity:AppCompatActivity(){private lateinit var b:ActivityHomeBinding;override fun onCreate(s:Bundle?){super.onCreate(s);if(!SessionManager(this).isLoggedIn()){startActivity(Intent(this,LoginActivity::class.java));finish();return};b=ActivityHomeBinding.inflate(layoutInflater);setContentView(b.root);b.bottomNavigation.setOnItemSelectedListener{when(it.itemId){R.id.nav_notes->show(NotesFragment());R.id.nav_account->show(AccountFragment());else->false}};if(s==null)b.bottomNavigation.selectedItemId=R.id.nav_notes}private fun show(f:Fragment):Boolean{supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragmentContainer,f).commit();return true}}
