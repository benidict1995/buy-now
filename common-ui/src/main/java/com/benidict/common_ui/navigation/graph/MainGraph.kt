package com.benidict.common_ui.navigation.graph

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import androidx.navigation.compose.composable
import com.benidict.common_ui.navigation.route.LandingRoute
import com.benidict.common_ui.navigation.route.SignInRoute

@Composable
fun MainGraph(navController: NavHostController, navRoute: @Composable (NavBackStackEntry, route: Any) -> Unit) {
    NavHost(navController, startDestination = LandingRoute) {
        composable<LandingRoute> {
            navRoute(it, LandingRoute)
        }
        composable<SignInRoute> {
            navRoute(it, SignInRoute)
        }
    }
}