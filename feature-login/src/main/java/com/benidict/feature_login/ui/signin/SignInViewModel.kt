package com.benidict.feature_login.ui.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state: MutableSharedFlow<SignInState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    fun checkEmailExists(email: String) {
        // Check if the email exists by trying to sign in
        viewModelScope.launch {
            try {
                val user = userRepository.checkIfEmailExists(email)
                Log.d(
                    "makerChecker",
                    "uid:${user.uid}"
                )
                _state.emit(
                    if (user.email.isNotEmpty()) {
                        SignInState.NavigateToPassword
                    } else SignInState.NavigateToSignUp
                )
            } catch (e: Exception) {
                Log.e("makerChecker", "Error checking email: ${e.message}")
            }
        }
    }
}