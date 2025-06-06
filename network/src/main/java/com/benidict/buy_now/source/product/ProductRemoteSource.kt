package com.benidict.buy_now.source.product

import com.benidict.buy_now.product.Product
import com.benidict.buy_now.service.FirebaseService
import com.benidict.buy_now.service.key.FirebaseKeys
import com.benidict.buy_now.service.key.FirebaseKeys.CATEGORY_ID_FIELD
import com.benidict.buy_now.service.key.FirebaseKeys.PRODUCT_ID_FIELD
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


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

    suspend fun getProductById(productId: Int): Product = suspendCoroutine { continuation ->
        firebaseService.getCollectionsWhereCondition(
            key = FirebaseKeys.PRODUCTS_KEY,
            field = PRODUCT_ID_FIELD,
            fieldValue = productId,
            onSuccess = { result ->
                val product = result.documents.mapNotNull { it.toObject(Product::class.java) }
                continuation.resume(product[0])
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