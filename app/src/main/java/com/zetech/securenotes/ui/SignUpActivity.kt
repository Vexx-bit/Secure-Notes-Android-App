package com.zetech.securenotes.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.zetech.securenotes.R
import com.zetech.securenotes.data.DatabaseHelper
import com.zetech.securenotes.databinding.ActivitySignUpBinding
import com.zetech.securenotes.util.Validators
import java.util.concurrent.Executors
class SignUpActivity:AppCompatActivity(){private lateinit var b:ActivitySignUpBinding;private val executor=Executors.newSingleThreadExecutor();override fun onCreate(s:Bundle?){super.onCreate(s);b=ActivitySignUpBinding.inflate(layoutInflater);setContentView(b.root);b.backButton.setOnClickListener{finish()};b.signUpButton.setOnClickListener{signUp()}}
 private fun signUp(){val name=b.nameInput.text?.toString().orEmpty();val email=b.emailInput.text?.toString().orEmpty();val pass=b.passwordInput.text?.toString().orEmpty();val confirm=b.confirmInput.text?.toString().orEmpty();b.nameLayout.error=Validators.nameError(name);b.emailLayout.error=Validators.emailError(email);b.passwordLayout.error=Validators.passwordError(pass);b.confirmLayout.error=if(confirm!=pass)getString(R.string.passwords_do_not_match) else null;if(listOf(b.nameLayout,b.emailLayout,b.passwordLayout,b.confirmLayout).any{it.error!=null})return;b.signUpButton.isEnabled=false;executor.execute{val db=DatabaseHelper(this);val result=if(db.emailExists(email))-2L else db.createUser(name,email,pass);runOnUiThread{b.signUpButton.isEnabled=true;when{result>0->{Snackbar.make(b.root,R.string.account_created,Snackbar.LENGTH_SHORT).show();b.root.postDelayed({finish()},600)};result==-2L->b.emailLayout.error=getString(R.string.email_registered);else->Snackbar.make(b.root,R.string.signup_failed,Snackbar.LENGTH_LONG).show()}}}}
 override fun onDestroy(){executor.shutdown();super.onDestroy()}}
