package com.benidict.buy_now

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){
    private val _isUserLoggedIn: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isUserLoggedIn = _isUserLoggedIn.asSharedFlow()

    init {
        checkIfUserLoggedIn()
    }

    private fun checkIfUserLoggedIn() {
        viewModelScope.launch {
            try {
                val ifUserLoggedIn = userRepository.isUserLoggedIn()
                _isUserLoggedIn.emit(ifUserLoggedIn.first())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}