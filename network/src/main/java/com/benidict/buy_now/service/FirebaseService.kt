package com.benidict.buy_now.service

import android.util.Log
import com.benidict.buy_now.cart.Cart
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.suspendCoroutine

class FirebaseService {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    suspend fun createUserWithEmailAndPassword(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password).await()

    suspend fun createUser(userMap: Map<String, String>) {
        FirebaseFirestore.getInstance().collection("users").document(userMap["uid"].orEmpty())
            .set(userMap).await()
    }

    fun signIn(
        email: String, password: String, onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
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

    fun addUpdateCartCollection(
        cart: Cart,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val db = FirebaseFirestore.getInstance()
        db.collection("cart")
            .whereEqualTo("uid", cart.uid)
            .limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    // No cart exists — add a new one
                    val newCart = cart
                    db.collection("cart")
                        .add(newCart)
                        .addOnSuccessListener {
                            Log.d("Firestore", "New cart created")
                            onSuccess()
                        }
                        .addOnFailureListener { exception ->
                            Log.e("Firestore", "Failed to add new cart", exception)
                            onError(exception)
                        }
                } else {
                    // Cart already exists — do nothing or log
                    Log.d("Firestore", "Cart already exists for uid: ${cart}")
                    updateCartCollection(cart = cart, onSuccess = {
                        onSuccess()
                    }, onError = {
                        onError(it)
                    })
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Failed to check cart", exception)
                onError(exception)
            }
    }


    private fun updateCartCollection(
        cart: Cart,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection("cart")
            .whereEqualTo("uid", cart.uid)
            .limit(1)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val documentRef = result.documents[0].reference
                    documentRef.update("products", cart.products)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { exception -> onError(exception) }
                } else {
                    onError(Exception("Cart not found for UID: ${cart.uid}"))
                }
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
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