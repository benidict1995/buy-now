package com.benidict.buy_now.source.user

import com.benidict.buy_now.cart.Cart
import com.benidict.buy_now.service.DataStoreService
import com.benidict.buy_now.service.key.DataStoreKeys
import com.benidict.buy_now.service.key.DataStoreKeys.CART
import com.benidict.buy_now.service.key.DataStoreKeys.EMAIL
import com.benidict.buy_now.service.key.DataStoreKeys.FIRST_NAME
import com.benidict.buy_now.service.key.DataStoreKeys.LAST_NAME
import com.benidict.buy_now.service.key.DataStoreKeys.UID
import com.benidict.buy_now.user.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class UserLocalSource @Inject constructor(
    private val dataStoreService: DataStoreService
) {

    suspend fun saveUserCart(cart: Cart) {
        dataStoreService.saveToDataStore(CART, Gson().toJson(cart))
    }

    suspend fun getUserCart(): Cart? {
        val json = dataStoreService.readFromDataStore(CART, "")
            .firstOrNull()
            ?.takeIf { it.isNotBlank() }

        return json?.let {
            try {
                Gson().fromJson(it, Cart::class.java)
            } catch (e: Exception) {
                null // or log the error
            }
        }
    }

    suspend fun saveUser(userMap: Map<String, String>) {
        userMap.map {
            dataStoreService.saveToDataStore(key = it.key, value = it.value)
        }
    }

    fun loadUser(): Flow<User> = combine(
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

    suspend fun userLoggedIn(isLoggedIn: Boolean) {
        dataStoreService.saveToDataStore(DataStoreKeys.LOGGED_IN, isLoggedIn)
    }

    suspend fun isUserLoggedIn() = dataStoreService.readFromDataStore(DataStoreKeys.LOGGED_IN, false)
}