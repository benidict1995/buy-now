package com.benidict.feature_profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.buy_now.user.User
import com.benidict.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _userDetailsState: MutableStateFlow<User> = MutableStateFlow(User())
    val userDetailsState = _userDetailsState.asStateFlow()

    init {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        viewModelScope.launch {
            try {
                val user = userRepository.loadUserDetails().first()
                Log.d("makerchecker", "useruser:${user.email}")
                _userDetailsState.value = user
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}