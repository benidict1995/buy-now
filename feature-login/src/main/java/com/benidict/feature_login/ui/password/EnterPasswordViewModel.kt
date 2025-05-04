package com.benidict.feature_login.ui.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltViewModel
class EnterPasswordViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableSharedFlow<EnterPasswordState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                validateLoginCredentials(email, password)
                _state.emit(EnterPasswordState.NavigateToHome)
            } catch (e: Exception) {
                _state.emit(EnterPasswordState.ShowError(e.message.orEmpty()))
            }
        }
    }

    private suspend fun validateLoginCredentials(email: String, password: String): Boolean =
        suspendCancellableCoroutine { cont ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    cont.resume(true)
                }
                .addOnFailureListener { exception ->
                    cont.resumeWithException(exception = exception)
                }
        }

}