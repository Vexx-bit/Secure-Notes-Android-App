package com.zetech.securenotes.ui
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.zetech.securenotes.data.DatabaseHelper
import com.zetech.securenotes.databinding.ActivityLoginBinding
import com.zetech.securenotes.util.SessionManager
import com.zetech.securenotes.util.Validators
import java.util.concurrent.Executors
class LoginActivity:AppCompatActivity(){private lateinit var b:ActivityLoginBinding;private val executor=Executors.newSingleThreadExecutor();override fun onCreate(s:Bundle?){super.onCreate(s);val session=SessionManager(this);if(session.isLoggedIn()){openHome();return};b=ActivityLoginBinding.inflate(layoutInflater);setContentView(b.root);b.loginButton.setOnClickListener{login()};b.signUpLink.setOnClickListener{startActivity(Intent(this,SignUpActivity::class.java))}}
 private fun login(){val email=b.emailInput.text?.toString().orEmpty();val password=b.passwordInput.text?.toString().orEmpty();b.emailLayout.error=Validators.emailError(email);b.passwordLayout.error=Validators.passwordError(password);if(b.emailLayout.error!=null||b.passwordLayout.error!=null)return;b.loginButton.isEnabled=false;executor.execute{val user=DatabaseHelper(this).authenticate(email,password);runOnUiThread{b.loginButton.isEnabled=true;if(user!=null){SessionManager(this).saveUser(user.id);openHome()}else Snackbar.make(b.root,getString(com.zetech.securenotes.R.string.invalid_credentials),Snackbar.LENGTH_LONG).show()}}}
 private fun openHome(){startActivity(Intent(this,HomeActivity::class.java));finish()} override fun onDestroy(){executor.shutdown();super.onDestroy()}}
