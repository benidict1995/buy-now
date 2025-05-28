package com.benidict.buy_now.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirebaseService {

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
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