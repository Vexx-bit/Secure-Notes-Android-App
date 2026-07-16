package com.zetech.securenotes.util
import android.util.Base64
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
object PasswordHasher {
 private const val ITERATIONS=120_000; private const val KEY_LENGTH=256
 fun newSalt(): String { val bytes=ByteArray(16); SecureRandom().nextBytes(bytes); return Base64.encodeToString(bytes, Base64.NO_WRAP) }
 fun hash(password:String,salt:String):String { val spec=PBEKeySpec(password.toCharArray(),Base64.decode(salt,Base64.NO_WRAP),ITERATIONS,KEY_LENGTH); val bytes=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).encoded; spec.clearPassword(); return Base64.encodeToString(bytes,Base64.NO_WRAP) }
 fun verify(password:String,salt:String,expected:String)=hash(password,salt)==expected
}
