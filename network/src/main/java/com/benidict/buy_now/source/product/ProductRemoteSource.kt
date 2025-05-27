package com.benidict.buy_now.source.product

import android.util.Log
import com.benidict.buy_now.product.Product
import com.benidict.buy_now.service.FirebaseService
import com.benidict.buy_now.service.key.FirebaseKeys
import com.benidict.buy_now.service.key.FirebaseKeys.CATEGORY_ID_FIELD
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.exp


class ProductRemoteSource @Inject constructor(private val firebaseService: FirebaseService) {

    suspend fun getProducts(): List<Product> = suspendCoroutine { continuation ->
        firebaseService.getCollection(FirebaseKeys.PRODUCTS_KEY,
            onSuccess = { result ->
                //val products = result.documents.mapNotNull { it.toObject(Product::class.java) }
                val products = Product.mapProductDTO(result.documents)
                continuation.resume(products)
            },
            onError = { error ->
                continuation.resumeWithException(Exception(error))
            }
        )
    }

    suspend fun getProductsByCategory(categoryId: Int): List<Product> =
        suspendCoroutine { continuation ->
            firebaseService.getCollectionsWhereCondition(
                FirebaseKeys.PRODUCTS_KEY,
                field = CATEGORY_ID_FIELD,
                fieldValue = categoryId,
                onSuccess = { result ->
                    val products = result.documents.mapNotNull { it.toObject(Product::class.java) }
                    continuation.resume(products)
                },
                onError = { error ->
                    continuation.resumeWithException(Exception(error))
                }
            )
        }
}