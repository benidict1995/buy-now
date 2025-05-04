package com.benidict.feature_login.ui.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableSharedFlow<SignInState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }


    fun checkEmailExists(email: String) {
        // Check if the email exists by trying to sign in
        viewModelScope.launch {
            try {
                val exists = checkIfEmailExists(email)
                Log.d(
                    "makerChecker",
                    "$email - ${if (exists) "Email exists!" else "Email does NOT exist."}"
                )
                _state.emit(
                    if (exists) {
                        SignInState.NavigateToPassword
                    } else SignInState.NavigateToSignUp
                )
            } catch (e: Exception) {
                Log.e("makerChecker", "Error checking email: ${e.message}")
            }
        }
    }

    private suspend fun checkIfEmailExists(email: String): Boolean =
        suspendCancellableCoroutine { cont ->
            db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnSuccessListener { result ->
                    cont.resume(result.isEmpty.not()) {}
                }
                .addOnFailureListener { exception ->
                    cont.resumeWithException(exception)
                }
        }
}