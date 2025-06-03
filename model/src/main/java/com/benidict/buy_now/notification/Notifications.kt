package com.benidict.buy_now.notification

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.getField

data class Notifications(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val type: String = "",
    var isRead: Boolean = false,
    val date: String = "",
    val userId: String = ""
) {
    companion object {
        fun mapNotificationDTO(documents: List<DocumentSnapshot>): List<Notifications> {
            val notificationList: MutableList<Notifications> = mutableListOf()
            documents.forEach { doc ->
                notificationList.add(
                    Notifications(
                        id = doc.getField<Int>("id") ?: 0,
                        title = doc.getField<String>("title") ?: "",
                        description = doc.getField<String>("description") ?: "",
                        userId = doc.getField<String>("userId") ?: "",
                        type = doc.getField<String>("type") ?: "",
                        date = doc.getField<String>("date") ?: "",
                        isRead = doc.getField<Boolean>("isRead") ?: false
                    )
                )
            }
            return notificationList.toList()
        }
    }
}