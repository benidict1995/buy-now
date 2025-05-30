package com.benidict.common_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.benidict.common_ui.navigation.route.CartGraph
import com.benidict.common_ui.navigation.route.FavoriteGraph
import com.benidict.common_ui.navigation.route.HomeGraph
import com.benidict.common_ui.navigation.route.NotificationRoute

@Composable
fun MainNavGraph(
    navController: NavHostController,
    navRoute: @Composable (NavBackStackEntry, route: Any, graph: Any) -> Unit
) {
    NavHost(navController = navController, startDestination = HomeGraph) {
        homeNavGraph { navBackStackEntry, route ->
            navRoute(navBackStackEntry, route, HomeGraph)
        }
        favoriteNavGraph { navBackStackEntry, route ->
            navRoute(navBackStackEntry, route, FavoriteGraph)
        }
        cartNavGraph { navBackStackEntry, route ->
            navRoute(navBackStackEntry, route, CartGraph)
        }
        notificationNavGraph { navBackStackEntry, route ->
            navRoute(navBackStackEntry, route, NotificationRoute)
        }
    }
}