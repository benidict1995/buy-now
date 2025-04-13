package com.benidict.common_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benidict.common_ui.navigation.route.HomeRoute
import com.benidict.common_ui.navigation.route.LandingRoute
import com.benidict.common_ui.navigation.route.SignInRoute
import com.benidict.common_ui.navigation.route.UserDetailsFormRoute
import com.benidict.common_utils.animation.fadeInAnimation
import com.benidict.common_utils.animation.fadeOutAnimation

@Composable
fun MainGraph(navController: NavHostController, navRoute: @Composable (NavBackStackEntry, route: Any) -> Unit) {
    NavHost(navController, startDestination = LandingRoute) {
        composable<LandingRoute> {
            navRoute(it, LandingRoute)
        }
        composable<SignInRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, SignInRoute)
        }
        composable<UserDetailsFormRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, UserDetailsFormRoute)
        }
        composable<HomeRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, HomeRoute)
        }
    }
}