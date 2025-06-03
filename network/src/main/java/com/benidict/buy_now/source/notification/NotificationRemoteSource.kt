package com.benidict.buy_now.source.notification

import com.benidict.buy_now.notification.Notifications
import com.benidict.buy_now.service.FirebaseService
import com.benidict.buy_now.service.key.FirebaseKeys
import com.benidict.buy_now.service.key.FirebaseKeys.USER_ID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NotificationRemoteSource @Inject constructor(private val firebaseService: FirebaseService) {
    suspend fun getNotificationByUserId(userId: String): List<Notifications> =
        suspendCoroutine { continuation ->
            firebaseService.getCollectionsWhereCondition(key = FirebaseKeys.NOTIFICATIONS_KEY,
                field = USER_ID,
                fieldValue = userId,
                onSuccess = { result ->
                    val notifications = Notifications.mapNotificationDTO(result.documents)
                    continuation.resume(notifications)
                },
                onError = { error ->
                    continuation.resumeWithException(Exception(error))
                })
        }
}