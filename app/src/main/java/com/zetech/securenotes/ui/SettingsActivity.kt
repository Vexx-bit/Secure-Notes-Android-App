package com.zetech.securenotes.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zetech.securenotes.R
import com.zetech.securenotes.databinding.ActivitySettingsBinding
import com.zetech.securenotes.util.ThemeManager
class SettingsActivity:AppCompatActivity(){private lateinit var b:ActivitySettingsBinding;override fun onCreate(s:Bundle?){super.onCreate(s);b=ActivitySettingsBinding.inflate(layoutInflater);setContentView(b.root);b.backButton.setOnClickListener{finish()};when(ThemeManager.saved(this)){ThemeManager.LIGHT->b.lightMode.isChecked=true;ThemeManager.DARK->b.darkMode.isChecked=true;else->b.systemMode.isChecked=true};b.themeGroup.setOnCheckedChangeListener{_,id->val mode=when(id){R.id.lightMode->ThemeManager.LIGHT;R.id.darkMode->ThemeManager.DARK;else->ThemeManager.SYSTEM};if(mode!=ThemeManager.saved(this))ThemeManager.save(this,mode)}}}
