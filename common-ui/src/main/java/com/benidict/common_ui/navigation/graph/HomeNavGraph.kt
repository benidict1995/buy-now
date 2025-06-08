package com.benidict.common_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.benidict.common_ui.navigation.route.HomeGraph
import com.benidict.common_ui.navigation.route.HomeScreenRoute
import com.benidict.common_ui.navigation.route.ProductDetailsScreenRoute
import com.benidict.common_ui.navigation.route.ProductListScreenRoute
import com.benidict.common_ui.navigation.route.SignInScreenRoute
import com.benidict.common_ui.navigation.route.ViewAllCategoryScreenRoute
import com.benidict.common_utils.animation.fadeInAnimation
import com.benidict.common_utils.animation.fadeOutAnimation

fun NavGraphBuilder.homeNavGraph(navRoute: @Composable (NavBackStackEntry, route: Any) -> Unit) {
    navigation<HomeGraph>(startDestination = HomeScreenRoute) {
        composable<HomeScreenRoute> {
            navRoute(it, HomeScreenRoute)
        }
        composable<SignInScreenRoute> {
            navRoute(it, SignInScreenRoute)
        }
        composable<ViewAllCategoryScreenRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, ViewAllCategoryScreenRoute)
        }
        composable<ProductDetailsScreenRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, ProductDetailsScreenRoute)
        }
        composable<ProductListScreenRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, ProductListScreenRoute)
        }
    }
}