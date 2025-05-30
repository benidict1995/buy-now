package com.benidict.common_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benidict.common_ui.navigation.route.LandingGraph
import com.benidict.common_ui.navigation.route.MainGraph
import com.benidict.common_ui.navigation.route.MainRoute

@Composable
fun BaseNavGraph(
    navController: NavHostController,
    navRoute: @Composable (NavBackStackEntry, route: Any, graph: Any) -> Unit
) {
    NavHost(navController, startDestination = LandingGraph) {
        landingNavGraph { navBackStackEntry, route ->
            navRoute(navBackStackEntry, route, LandingGraph)
        }

        composable<MainRoute> {
            navRoute(it, MainRoute, MainGraph)
        }


     /**   composable<LandingRoute> {
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
        composable<EnterPasswordRoute>(
            enterTransition = {
                fadeInAnimation(this)
            },
            exitTransition = {
                fadeOutAnimation(this)
            }
        ) {
            navRoute(it, EnterPasswordRoute)
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

        //Start Tab
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
        composable<NotificationListRoute> {
            navRoute(it, NotificationListRoute)
        }
        //End Tab



      **/
    }
}