package com.benidict.data.repository.notifications

import com.benidict.buy_now.notification.Notifications
import com.benidict.buy_now.source.notification.NotificationRemoteSource
import javax.inject.Inject

class NotificationsRepository @Inject constructor(
    private val notificationRemoteSource: NotificationRemoteSource
) {
    suspend fun getNotificationDetailsById(id: Int): Notifications {
        return notificationRemoteSource.getNotificationDetailsById(id)
    }

    suspend fun getNotificationByUserId(userId: String): List<Notifications> {
        return notificationRemoteSource.getNotificationByUserId(userId)
    }
}