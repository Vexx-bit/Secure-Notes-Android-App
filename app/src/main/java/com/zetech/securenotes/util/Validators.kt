package com.zetech.securenotes.util
import android.util.Patterns
object Validators { fun emailError(email:String)=when{email.isBlank()->"Email is required";!Patterns.EMAIL_ADDRESS.matcher(email).matches()->"Enter a valid email";else->null};fun passwordError(password:String)=when{password.isBlank()->"Password is required";password.length<8->"Use at least 8 characters";else->null};fun nameError(name:String)=if(name.trim().length<2)"Enter your full name" else null }
