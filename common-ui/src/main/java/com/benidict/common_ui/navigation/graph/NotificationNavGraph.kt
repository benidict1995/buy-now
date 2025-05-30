package com.benidict.common_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.benidict.common_ui.navigation.route.NotificationGraph
import com.benidict.common_ui.navigation.route.NotificationScreenRoute

fun NavGraphBuilder.notificationNavGraph(navRoute: @Composable (NavBackStackEntry, route: Any) -> Unit) {
    navigation<NotificationGraph>(startDestination = NotificationScreenRoute) {
        composable<NotificationScreenRoute> {
            navRoute(it, NotificationScreenRoute)
        }
    }
}