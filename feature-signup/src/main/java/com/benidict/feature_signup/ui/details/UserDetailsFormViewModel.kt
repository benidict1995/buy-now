package com.benidict.feature_signup.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.common_utils.validation.isEmailValid
import com.benidict.common_utils.validation.isFieldsEmpty
import com.benidict.data.repository.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.benidict.buy_now.user.UserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailsFormViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state: MutableSharedFlow<UserDetailsState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    fun isFormFieldsValid(
        email: String,
        firstName: String,
        lastName: String,
        isPasswordConfirmed: Boolean
    ): Boolean {
        return isEmailValid(email) && isFieldsEmpty(
            listOf(
                firstName,
                lastName,
                email
            )
        ).not() && isPasswordConfirmed
    }

    fun isPasswordConfirmed(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun createUser(firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                userRepository.createUser(
                    UserRequest(
                        email = email,
                        password = password,
                        firstname = firstName,
                        lastname = lastName
                    )
                )

                withContext(Dispatchers.Main) {
                    _state.emit(
                        UserDetailsState.NavigateToHome
                    )
                }
                /** val result = auth.createUserWithEmailAndPassword(email, password).await()
                result.user?.uid?.let {
                Log.d("makerChecker", "uid:$it")
                val userMap = mapOf(
                "uid" to it,
                "firstname" to firstName,
                "lastname" to lastName,
                "email" to email
                )
                FirebaseFirestore.getInstance().collection("users").document(it)
                .set(userMap).await()

                withContext(Dispatchers.Main) {
                Log.d("makerChecker", "user-result")
                _state.emit(
                UserDetailsState.NavigateToHome
                )
                }
                }**/
            } catch (e: Exception) {
                _state.emit(UserDetailsState.ShowError(e.message.orEmpty()))
            }
        }
    }
}