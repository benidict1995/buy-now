package com.benidict.buy_now.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.benidict.buy_now.navigation.main.MainScreen
import com.benidict.common_ui.navigation.graph.landingNavGraph
import com.benidict.common_ui.navigation.route.EnterPasswordScreenRoute
import com.benidict.common_ui.navigation.route.LandingGraph
import com.benidict.common_ui.navigation.route.LandingScreenRoute
import com.benidict.common_ui.navigation.route.MainRoute
import com.benidict.common_ui.navigation.route.SignInScreenRoute
import com.benidict.common_ui.navigation.route.UserDetailsFormScreenRoute
import com.benidict.feature_login.ui.landing.LandingScreen
import com.benidict.feature_login.ui.password.EnterPasswordScreen
import com.benidict.feature_login.ui.signin.SignInScreen
import com.benidict.feature_signup.ui.details.UserDetailsFormScreen

@Composable
fun BaseNavGraph(
    navController: NavHostController
) {
    NavHost(navController, startDestination = LandingGraph) {
        landingNavGraph { navBackStackEntry, route ->
            when (route) {
                LandingScreenRoute -> {
                    LandingScreen(onContinue = {
                        navController.navigate(SignInScreenRoute)
                    }, onContinueAsGuest = {
                        navController.navigate(MainRoute) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    })
                }

                SignInScreenRoute -> {
                    SignInScreen(navController,
                        onNavigateToPassword = { email ->
                            navController.navigate(EnterPasswordScreenRoute(email = email))
                        },
                        onNavigateToSignUp = { email ->
                            navController.navigate(UserDetailsFormScreenRoute(email = email))
                        })
                }

                UserDetailsFormScreenRoute -> {
                    val param: UserDetailsFormScreenRoute = navBackStackEntry.toRoute()
                    UserDetailsFormScreen(
                        emailAddress = param.email,
                        navController = navController,
                        onNext = {
                            navController.navigate(MainRoute) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        })
                }

                EnterPasswordScreenRoute -> {
                    val param: EnterPasswordScreenRoute = navBackStackEntry.toRoute()
                    EnterPasswordScreen(
                        email = param.email,
                        navController, onNext = {
                            navController.navigate(MainRoute) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        })
                }
            }
        }

        composable<MainRoute> {
            MainScreen()
        }
    }
}