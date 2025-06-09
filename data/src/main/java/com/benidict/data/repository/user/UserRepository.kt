package com.benidict.data.repository.user

import com.benidict.buy_now.service.key.DataStoreKeys.EMAIL
import com.benidict.buy_now.service.key.DataStoreKeys.FIRST_NAME
import com.benidict.buy_now.service.key.DataStoreKeys.LAST_NAME
import com.benidict.buy_now.service.key.DataStoreKeys.UID
import com.benidict.buy_now.source.user.UserLocalSource
import com.benidict.buy_now.source.user.UserRemoteSource
import com.benidict.buy_now.user.User
import com.benidict.buy_now.user.UserRequest
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteSource: UserRemoteSource,
                                         private val userLocalSource: UserLocalSource){

    suspend fun createUser(userRequest: UserRequest) {
        userRemoteSource.createUser(userRequest)
    }

    suspend fun validateLoginCredentials(email: String, password: String): Boolean {
        val user = userRemoteSource.validateLoginCredentials(email, password)
        val userMap: Map<String, String> = mapOf(
            EMAIL to user.email,
            FIRST_NAME to user.firstname,
            LAST_NAME to user.lastname,
            UID to user.uid
        )
        userLocalSource.saveUser(userMap)
        userLocalSource.userLoggedIn(isLoggedIn = user.email.isNotEmpty() && user.uid.isNotEmpty())
        return user.email.isNotEmpty()
    }

    suspend fun isUserLoggedIn() = userLocalSource.isUserLoggedIn()

    suspend fun loadUserDetails() = userLocalSource.loadUser()

    suspend fun checkIfEmailExists(email: String): User {
        return userRemoteSource.checkIfEmailExists(email)
    }
}