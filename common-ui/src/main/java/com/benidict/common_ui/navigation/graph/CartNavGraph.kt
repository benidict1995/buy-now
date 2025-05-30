package com.benidict.common_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.benidict.common_ui.navigation.route.CartGraph
import com.benidict.common_ui.navigation.route.CartScreenRoute

fun NavGraphBuilder.cartNavGraph(navRoute: @Composable (NavBackStackEntry, route: Any) -> Unit) {
    navigation<CartGraph>(startDestination = CartScreenRoute) {
        composable<CartScreenRoute> {
            navRoute(it, CartScreenRoute)
        }
    }
}