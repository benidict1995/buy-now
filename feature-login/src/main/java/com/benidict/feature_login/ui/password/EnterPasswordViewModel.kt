package com.benidict.feature_login.ui.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.repository.cart.CartRepository
import com.benidict.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class EnterPasswordViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _state: MutableSharedFlow<EnterPasswordState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                userRepository.validateLoginCredentials(email, password)
                cartRepository.loadCart()
                _state.emit(EnterPasswordState.NavigateToHome)
            } catch (e: Exception) {
                _state.emit(EnterPasswordState.ShowError(e.message.orEmpty()))
            }
        }
    }

}