package com.benidict.feature_notification.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.buy_now.notification.Notifications
import com.benidict.data.repository.notifications.NotificationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationListViewModel @Inject constructor(
    private val notificationsRepository: NotificationsRepository
) : ViewModel() {

    private val _notificationState: MutableStateFlow<List<Notifications>> = MutableStateFlow(emptyList())
    val notificationState = _notificationState.asStateFlow()

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            val notifications = notificationsRepository.getNotificationByUserId(
                userId = "H5TeqmtNvePUsM9LTDNp2qbwOUD3"
            )
            _notificationState.value = notifications
        }
    }
}