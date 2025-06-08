package com.benidict.buy_now.source.auth

import com.benidict.buy_now.service.DataStoreService
import com.benidict.buy_now.service.key.DataStoreKeys
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthLocalSource @Inject constructor(
    private val dataStoreService: DataStoreService
) {
    suspend fun userLoggedIn(isLoggedIn: Boolean) {
        dataStoreService.saveToDataStore(DataStoreKeys.LOGGED_IN, isLoggedIn)
    }

    suspend fun isUserLoggedIn() = dataStoreService.readFromDataStore(DataStoreKeys.LOGGED_IN, false)
}