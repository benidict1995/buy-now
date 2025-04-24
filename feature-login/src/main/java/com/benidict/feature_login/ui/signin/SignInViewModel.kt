package com.benidict.feature_login.ui.signin

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel() {

     private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }


    fun checkEmailExists(email: String) {
        // Check if the email exists by trying to sign in
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (signInMethods.isNullOrEmpty()) {
                        Log.d("makerChecker", "email is doesn't exists")
                        // Email doesn't exist, show an error or guide the user to sign up
                    } else {
                        // Email exists, try to sign in
                        Log.d("makerChecker", "email is exists")
                    }
                } else {
                    // Failed to check the email, show an error
                    Log.d("makerChecker", "Failed to check email: ${task.exception?.message}")
                }
            }
    }
}