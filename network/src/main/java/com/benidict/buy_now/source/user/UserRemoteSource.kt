package com.benidict.buy_now.source.user

import android.util.Log
import com.benidict.buy_now.service.FirebaseService
import com.benidict.buy_now.service.key.FirebaseKeys
import com.benidict.buy_now.service.key.FirebaseKeys.EMAIL_FIELD
import com.benidict.buy_now.user.User
import com.benidict.buy_now.user.UserRequest
import com.benidict.buy_now.user.UserRequest.Companion.toUserMap
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRemoteSource @Inject constructor(private val firebaseService: FirebaseService) {

    suspend fun createUser(userRequest: UserRequest) {
        val result = firebaseService.createUserWithEmailAndPassword(userRequest.email, userRequest.password)
        result.user?.uid?.let {
            val newUser = userRequest.copy(uid = it)
            firebaseService.createUser(newUser.toUserMap())
        }
    }

    suspend fun validateLoginCredentials(email: String, password: String): User =
        suspendCoroutine { continuation ->
            firebaseService.signIn(email, password, onSuccess = {
                firebaseService.getCollectionsWhereCondition(
                    key = FirebaseKeys.USERS_KEY,
                    field = EMAIL_FIELD,
                    fieldValue = email,
                    onSuccess = { result ->
                        val user = result.documents.mapNotNull { it.toObject(User::class.java) }
                        continuation.resume(user[0])
                    },
                    onError = { error ->
                        continuation.resumeWithException(Exception(error))
                    }
                )
            }, onError = { error ->
                continuation.resumeWithException(Exception(error))
            })
        }

    suspend fun checkIfEmailExists(email: String): User =
        suspendCoroutine { continuation ->
            firebaseService.getCollectionsWhereCondition(
                key = FirebaseKeys.USERS_KEY,
                field = EMAIL_FIELD,
                fieldValue = email,
                onSuccess = { result ->
                    val user = result.documents.mapNotNull { it.toObject(User::class.java) }
                    continuation.resume(user[0])
                },
                onError = { error ->
                    continuation.resumeWithException(Exception(error))
                }
            )
        }
}