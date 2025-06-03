package com.benidict.feature_notification.details

import android.util.Log
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
class NotificationDetailsViewModel @Inject constructor(
    private val notificationsRepository: NotificationsRepository
) : ViewModel() {

    private val _notificationState: MutableStateFlow<Notifications> = MutableStateFlow(Notifications())
    val notificationState = _notificationState.asStateFlow()

    fun loadNotificationDetails(id: Int) {
        viewModelScope.launch {
            try {
                val notification = notificationsRepository.getNotificationDetailsById(id)
                _notificationState.value = notification
            } catch (e: Exception) {

            }
        }
    }
}