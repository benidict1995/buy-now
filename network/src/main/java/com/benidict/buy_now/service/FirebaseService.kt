package com.benidict.buy_now.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class FirebaseService {

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun getCollectionsWhereCondition(
        key: String,
        field: String,
        fieldValue: String,
        onSuccess: (QuerySnapshot) -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection(key)
            .whereEqualTo(field, fieldValue)
            .get()
            .addOnSuccessListener { result -> onSuccess(result) }
            .addOnFailureListener { exception -> onError(exception) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
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