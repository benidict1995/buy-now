package com.benidict.buy_now.source.user

import com.benidict.buy_now.service.DataStoreService
import com.benidict.buy_now.service.key.DataStoreKeys.EMAIL
import com.benidict.buy_now.service.key.DataStoreKeys.FIRST_NAME
import com.benidict.buy_now.service.key.DataStoreKeys.LAST_NAME
import com.benidict.buy_now.service.key.DataStoreKeys.UID
import com.benidict.buy_now.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class UserLocalSource @Inject constructor(
    private val dataStoreService: DataStoreService
) {

    suspend fun saveUser(userMap: Map<String, String>) {
        userMap.map {

        }
    }

    fun readUser(): Flow<User> = combine(
        dataStoreService.readFromDataStore(FIRST_NAME, ""),
                dataStoreService.readFromDataStore(LAST_NAME, ""),
        dataStoreService.readFromDataStore(EMAIL, ""),
        dataStoreService.readFromDataStore(UID, "")
    ) { firstName, lastName, email, uid ->
        User(
            firstname = firstName,
            lastname = lastName,
            email = email,
            uid =  uid)
    }
}