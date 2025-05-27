package com.benidict.buy_now

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.benidict.buy_now.ui.theme.BuynowTheme
import com.benidict.common_ui.navigation.graph.MainGraph
import com.benidict.common_ui.navigation.route.EnterPasswordRoute
import com.benidict.common_ui.navigation.route.HomeRoute
import com.benidict.common_ui.navigation.route.LandingRoute
import com.benidict.common_ui.navigation.route.ProductDetailsRoute
import com.benidict.common_ui.navigation.route.SignInRoute
import com.benidict.common_ui.navigation.route.UserDetailsFormRoute
import com.benidict.common_ui.navigation.route.ViewAllCategoryRoute
import com.benidict.feature_category.CategoriesScreen
import com.benidict.feature_home.home.HomeScreen
import com.benidict.feature_login.ui.landing.LandingScreen
import com.benidict.feature_login.ui.password.EnterPasswordScreen
import com.benidict.feature_login.ui.signin.SignInScreen
import com.benidict.feature_product.details.ProductDetailsScreen
import com.benidict.feature_signup.ui.details.UserDetailsFormScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            BuynowTheme {
                MainGraph(navController) { navBackStackEntry, route ->
                    when (route) {
                        LandingRoute -> {
                            LandingScreen(onContinue = {
                                navController.navigate(SignInRoute)
                            }, onContinueAsGuest = {
                                navController.navigate(HomeRoute) {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                }
                            })
                        }

                        SignInRoute -> {
                            SignInScreen(navController,
                                onNavigateToPassword = { email ->
                                    navController.navigate(EnterPasswordRoute(email = email))
                                },
                                onNavigateToSignUp = { email ->
                                    navController.navigate(UserDetailsFormRoute(email = email))
                                })
                        }

                        UserDetailsFormRoute -> {
                            val param: UserDetailsFormRoute = navBackStackEntry.toRoute()
                            UserDetailsFormScreen(
                                emailAddress = param.email,
                                navController = navController,
                                onNext = {
                                    navController.navigate(HomeRoute) {
                                        popUpTo(0) {
                                            inclusive = true
                                        }
                                    }
                                })
                        }

                        EnterPasswordRoute -> {
                            val param: EnterPasswordRoute = navBackStackEntry.toRoute()
                            EnterPasswordScreen(
                                email = param.email,
                                navController, onNext = {
                                navController.navigate(HomeRoute) {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                }
                            })
                        }

                        HomeRoute -> {
                            HomeScreen(navController, onViewAllCategories = {
                                navController.navigate(ViewAllCategoryRoute)
                            }, onNavigateProductDetails = { productId ->
                                navController.navigate(ProductDetailsRoute(productId = productId))
                            })
                        }

                        ViewAllCategoryRoute -> {
                            CategoriesScreen(navController)
                        }

                        ProductDetailsRoute -> {
                            val param: ProductDetailsRoute = navBackStackEntry.toRoute()
                            ProductDetailsScreen(navController, param.productId)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuynowTheme {

    }
}