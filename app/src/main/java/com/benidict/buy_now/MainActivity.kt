package com.benidict.buy_now

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.benidict.buy_now.ui.theme.BuynowTheme
import com.benidict.common_ui.navigation.graph.BaseNavGraph
import com.benidict.common_ui.navigation.main.MainScreen
import com.benidict.common_ui.navigation.route.CartGraph
import com.benidict.common_ui.navigation.route.EnterPasswordScreenRoute
import com.benidict.common_ui.navigation.route.FavoriteGraph
import com.benidict.common_ui.navigation.route.HomeGraph
import com.benidict.common_ui.navigation.route.HomeRoute
import com.benidict.common_ui.navigation.route.HomeScreenRoute
import com.benidict.common_ui.navigation.route.LandingGraph
import com.benidict.common_ui.navigation.route.LandingScreenRoute
import com.benidict.common_ui.navigation.route.MainGraph
import com.benidict.common_ui.navigation.route.MainRoute
import com.benidict.common_ui.navigation.route.ProductDetailsScreenRoute
import com.benidict.common_ui.navigation.route.ProductListScreenRoute
import com.benidict.common_ui.navigation.route.SignInScreenRoute
import com.benidict.common_ui.navigation.route.UserDetailsFormScreenRoute
import com.benidict.common_ui.navigation.route.ViewAllCategoryScreenRoute
import com.benidict.feature_category.CategoriesScreen
import com.benidict.feature_home.home.HomeScreen
import com.benidict.feature_login.ui.landing.LandingScreen
import com.benidict.feature_login.ui.password.EnterPasswordScreen
import com.benidict.feature_login.ui.signin.SignInScreen
import com.benidict.feature_product.details.ProductDetailsScreen
import com.benidict.feature_product.list.ProductListScreen
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
                BaseNavGraph(navController) { navBackStackEntry, route, graph ->

                    when (graph) {
                        LandingGraph -> {
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

                        MainGraph -> {
                            if (route == MainRoute) {
                                MainScreen { mainNavBackStackEntry, mainRoute, mainGraph ->
                                    when (mainGraph) {
                                        HomeGraph -> {
                                            when(mainRoute) {
                                                HomeScreenRoute -> {
                                                    HomeScreen(onViewAllCategories = {
                                                        navController.navigate(
                                                            ViewAllCategoryScreenRoute
                                                        )
                                                    }, onNavigateProductDetails = { productId ->
                                                        navController.navigate(
                                                            ProductDetailsScreenRoute(productId = productId)
                                                        )
                                                    }, onNavigateProductByCategory = { categoryId, categoryName ->
                                                        navController.navigate(
                                                            ProductListScreenRoute(
                                                                categoryId = categoryId,
                                                                categoryName = categoryName
                                                            )
                                                        )
                                                    })
                                                }
                                                ViewAllCategoryScreenRoute -> {
                                                    CategoriesScreen(
                                                        navController,
                                                        onNavigateToProductList = { categoryId, categoryName ->
                                                            navController.navigate(
                                                                ProductListScreenRoute(
                                                                    categoryId = categoryId,
                                                                    categoryName = categoryName
                                                                )
                                                            )
                                                        })
                                                }
                                                ProductListScreenRoute -> {
                                                    val param: ProductListScreenRoute = mainNavBackStackEntry.toRoute()
                                                    ProductListScreen(
                                                        navController,
                                                        categoryId = param.categoryId,
                                                        categoryName = param.categoryName
                                                    ) { productId ->
                                                        navController.navigate(ProductDetailsScreenRoute(productId = productId))
                                                    }
                                                }
                                                ProductDetailsScreenRoute -> {
                                                    val param: ProductDetailsScreenRoute = mainNavBackStackEntry.toRoute()
                                                    ProductDetailsScreen(navController, param.productId)
                                                }
                                            }
                                        }

                                        FavoriteGraph -> {

                                        }

                                        CartGraph -> {

                                        }

                                    }
                                }
                            }
                        }
                    }

                  /**  when (route) {
                        HomeRoute -> {
                            HomeScreen(onViewAllCategories = {
                                navController.navigate(ViewAllCategoryRoute)
                            }, onNavigateProductDetails = { productId ->
                                navController.navigate(ProductDetailsRoute(productId = productId))
                            }, onNavigateProductByCategory = { categoryId, categoryName ->
                                navController.navigate(
                                    ProductListRoute(
                                        categoryId = categoryId,
                                        categoryName = categoryName
                                    )
                                )
                            })
                        }

                        ViewAllCategoryRoute -> {
                            CategoriesScreen(
                                navController,
                                onNavigateToProductList = { categoryId, categoryName ->
                                    navController.navigate(
                                        ProductListRoute(
                                            categoryId = categoryId,
                                            categoryName = categoryName
                                        )
                                    )
                                })
                        }

                        ProductDetailsRoute -> {
                            val param: ProductDetailsRoute = navBackStackEntry.toRoute()
                            ProductDetailsScreen(navController, param.productId)
                        }

                        ProductListRoute -> {
                            val param: ProductListRoute = navBackStackEntry.toRoute()
                            ProductListScreen(
                                navController,
                                categoryId = param.categoryId,
                                categoryName = param.categoryName
                            ) { productId ->
                                navController.navigate(ProductDetailsRoute(productId = productId))
                            }
                        }

                        NotificationListRoute -> {
                            NotificationListScreen(navController)
                        }
                    } **/
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