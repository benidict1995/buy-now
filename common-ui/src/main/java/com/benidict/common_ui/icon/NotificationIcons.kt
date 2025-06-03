package com.benidict.common_ui.icon

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.buy_now.notification.NotificationType
import com.benidict.common_ui.R

@Composable
fun NotificationIcons(notificationType: String) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .padding(vertical = 10.dp, horizontal = 8.dp)
            .background(
                color = Color.Cyan, shape = CircleShape
            )
    ) {
        Icon(
            modifier = Modifier.size(34.dp).align(Alignment.Center),
            painter = painterResource(notificationType.notificationIconType()),
            contentDescription = "notification",
            tint = Color.Unspecified
        )
    }
}

private fun String.notificationIconType(): Int {
    return when (this) {
        NotificationType.ALERT.type -> {
            R.drawable.ic_alert
        }

        NotificationType.DEALS.type -> {
            R.drawable.ic_deals
        }

        NotificationType.NEW_ARRIVAL.type -> {
            R.drawable.ic_new_arrival
        }

        NotificationType.ANNOUNCEMENT.type -> {
            R.drawable.ic_announcement
        }

        else -> {
            R.drawable.ic_alert
        }
    }
}