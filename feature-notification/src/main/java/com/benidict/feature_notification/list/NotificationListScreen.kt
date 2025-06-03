package com.benidict.feature_notification.list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.common_ui.card.NotificationCard
import com.benidict.common_ui.icon.CircleBackButton
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite

@Composable
fun NotificationListScreen(navController: NavHostController, onNavigateToNotificationDetails: (Int) -> Unit) {
    val viewModel = hiltViewModel<NotificationListViewModel>()
    val notifications = viewModel.notificationState.collectAsState()
    Log.d("makerChecker", "NotificationListScreen")
    MainLayout(
        containerColor = GrayishWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .systemBarsPadding()
                .padding(bottom = 46.dp)
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Notifications", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center).padding(top = 12.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(notifications.value) { notification ->
                    NotificationCard(notification, onClick = { id ->
                        onNavigateToNotificationDetails(id)
                    })
                }
            }
        }
    }
}