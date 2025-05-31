package com.benidict.common_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.benidict.common_ui.navigation.route.EnterPasswordRoute
import com.benidict.common_ui.navigation.route.EnterPasswordScreenRoute
import com.benidict.common_ui.navigation.route.LandingGraph
import com.benidict.common_ui.navigation.route.LandingScreenRoute
import com.benidict.common_ui.navigation.route.SignInRoute
import com.benidict.common_ui.navigation.route.SignInScreenRoute
import com.benidict.common_ui.navigation.route.UserDetailsFormRoute
import com.benidict.common_ui.navigation.route.UserDetailsFormScreenRoute
import com.benidict.common_utils.animation.fadeInAnimation
import com.benidict.common_utils.animation.fadeOutAnimation

fun NavGraphBuilder.landingNavGraph(navRoute: @Composable (NavBackStackEntry, route: Any) -> Unit) {
    navigation<LandingGraph>(startDestination = LandingScreenRoute) {
        composable<LandingScreenRoute> {
            navRoute(it, LandingScreenRoute)
        }

        composable<SignInScreenRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, SignInScreenRoute)
        }
        composable<EnterPasswordScreenRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, EnterPasswordScreenRoute)
        }

        composable<UserDetailsFormScreenRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, UserDetailsFormScreenRoute)
        }
    }
}