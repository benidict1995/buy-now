package com.benidict.common_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.benidict.common_ui.navigation.route.FavoriteGraph
import com.benidict.common_ui.navigation.route.FavoriteScreenRoute

fun NavGraphBuilder.favoriteNavGraph(navRoute: @Composable (NavBackStackEntry, route: Any) -> Unit) {
    navigation<FavoriteGraph>(startDestination = FavoriteScreenRoute) {
        composable<FavoriteScreenRoute> {
            navRoute(it, FavoriteScreenRoute)
        }
    }
}