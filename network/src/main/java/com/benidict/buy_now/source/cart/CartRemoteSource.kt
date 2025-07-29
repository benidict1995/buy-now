package com.benidict.buy_now.source.cart

import android.util.Log
import com.benidict.buy_now.cart.Cart
import com.benidict.buy_now.product.Product
import com.benidict.buy_now.service.FirebaseService
import com.benidict.buy_now.service.key.FirebaseKeys
import com.benidict.buy_now.user.User
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CartRemoteSource @Inject constructor(
    private val firebaseService: FirebaseService
) {

    suspend fun addToCart(cart: Cart) {
        suspendCoroutine { continuation ->
            firebaseService.addUpdateCartCollection(cart, onSuccess = {
                continuation.resume("")
            }, onError = { error ->
                continuation.resumeWithException(Exception(error))
            })
        }
    }

    suspend fun loadCart(uid: String): Cart =
        suspendCoroutine { continuation ->
            firebaseService.getCollectionsWhereCondition(
                key = FirebaseKeys.CART_KEY,
                field = FirebaseKeys.UID_FIELD,
                fieldValue = uid,
                onSuccess = { result ->
                    val cart = result.documents.mapNotNull { it.toObject(Cart::class.java) }
                    continuation.resume(if (cart.isNotEmpty()) cart[0] else Cart.empty())
                },
                onError = { error ->
                    continuation.resumeWithException(Exception(error))
                }
            )
        }
}