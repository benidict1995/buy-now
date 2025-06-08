package com.benidict.buy_now.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.suspendCoroutine

class FirebaseService {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    suspend fun createUserWithEmailAndPassword(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password).await()

    suspend fun createUser(userMap: Map<String, String>) {
        FirebaseFirestore.getInstance().collection("users").document(userMap["uid"].orEmpty())
            .set(userMap).await()
    }

    fun signIn(email: String, password: String, onSuccess: () -> Unit,
               onError: (Exception) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }

    fun <T> getCollectionsWhereCondition(
        key: String,
        field: String,
        fieldValue: T,
        onSuccess: (QuerySnapshot) -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection(key)
            .whereEqualTo(field, fieldValue)
            .get()
            .addOnSuccessListener { result -> onSuccess(result) }
            .addOnFailureListener { exception -> onError(exception) }
    }

    fun getCollection(
        key: String,
        onSuccess: (QuerySnapshot) -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection(key)
            .get()
            .addOnSuccessListener { result ->
                onSuccess(result)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }
}