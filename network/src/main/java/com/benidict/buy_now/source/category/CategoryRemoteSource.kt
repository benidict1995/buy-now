package com.benidict.buy_now.source.category

import com.benidict.buy_now.category.Category
import com.benidict.buy_now.service.FirebaseService
import com.benidict.buy_now.service.key.FirebaseKeys
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CategoryRemoteSource @Inject constructor(private val firebaseService: FirebaseService) {

    suspend fun getCategories(): List<Category> = suspendCoroutine { continuation ->
        firebaseService.getCollection(
            key = FirebaseKeys.CATEGORY_KEY,
            onSuccess = { result ->
                val categories = result.documents.mapNotNull { it.toObject(Category::class.java) }
                continuation.resume(categories)
            },
            onError = { error ->
                continuation.resumeWithException(Exception(error))
            }
        )
    }
}