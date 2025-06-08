package com.benidict.data.repository.auth

import com.benidict.buy_now.source.auth.AuthLocalSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authLocalSource: AuthLocalSource
) {
    suspend fun saveIsUserLoggedIn(isLoggedIn: Boolean) {
        authLocalSource.userLoggedIn(isLoggedIn)
    }

    suspend fun isUserLoggedIn() = authLocalSource.isUserLoggedIn()
}