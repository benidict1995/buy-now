package com.benidict.data.repository.user

import com.benidict.buy_now.source.user.UserRemoteSource
import com.benidict.buy_now.user.User
import com.benidict.buy_now.user.UserRequest
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteSource: UserRemoteSource){

    suspend fun createUser(userRequest: UserRequest) {
        userRemoteSource.createUser(userRequest)
    }

    suspend fun validateLoginCredentials(email: String, password: String): Boolean {
        return userRemoteSource.validateLoginCredentials(email, password)
    }

    suspend fun checkIfEmailExists(email: String): User {
        return userRemoteSource.checkIfEmailExists(email)
    }
}