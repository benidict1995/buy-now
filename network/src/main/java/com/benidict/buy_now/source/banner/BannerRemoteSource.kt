package com.benidict.buy_now.source.banner

import com.benidict.buy_now.banner.Banner
import com.benidict.buy_now.service.FirebaseService
import com.benidict.buy_now.service.key.FirebaseKeys
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class BannerRemoteSource @Inject constructor(private val firebaseService: FirebaseService) {

    suspend fun getBanners(): List<Banner> = suspendCoroutine { continuation ->
        firebaseService.getCollection(
            key = FirebaseKeys.DASHBOARD_BANNER_KEY,
            onSuccess = { result ->
                val banners = result.documents.mapNotNull { it.toObject(Banner::class.java) }
                continuation.resume(banners)
            },
            onError = { error ->
                continuation.resumeWithException(Exception(error))
            }
        )
    }
}