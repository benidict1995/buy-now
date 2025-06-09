package com.benidict.buy_now.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import com.benidict.buy_now.default.DefaultScreen
import com.benidict.common_ui.navigation.graph.cartNavGraph
import com.benidict.common_ui.navigation.graph.favoriteNavGraph
import com.benidict.common_ui.navigation.graph.homeNavGraph
import com.benidict.common_ui.navigation.graph.notificationNavGraph
import com.benidict.common_ui.navigation.route.CartGraph
import com.benidict.common_ui.navigation.route.CartScreenRoute
import com.benidict.common_ui.navigation.route.EnterPasswordScreenRoute
import com.benidict.common_ui.navigation.route.FavoriteGraph
import com.benidict.common_ui.navigation.route.FavoriteScreenRoute
import com.benidict.common_ui.navigation.route.HomeGraph
import com.benidict.common_ui.navigation.route.HomeScreenRoute
import com.benidict.common_ui.navigation.route.NotificationDetailsScreenRoute
import com.benidict.common_ui.navigation.route.NotificationScreenRoute
import com.benidict.common_ui.navigation.route.ProductDetailsScreenRoute
import com.benidict.common_ui.navigation.route.ProductListScreenRoute
import com.benidict.common_ui.navigation.route.ProfileScreenRoute
import com.benidict.common_ui.navigation.route.SignInScreenRoute
import com.benidict.common_ui.navigation.route.UserDetailsFormScreenRoute
import com.benidict.common_ui.navigation.route.ViewAllCategoryScreenRoute
import com.benidict.feature_cart.CartScreen
import com.benidict.feature_category.CategoriesScreen
import com.benidict.feature_favorite.FavoriteScreen
import com.benidict.feature_home.home.HomeScreen
import com.benidict.feature_login.ui.signin.SignInScreen
import com.benidict.feature_notification.details.NotificationDetailsScreen
import com.benidict.feature_notification.list.NotificationListScreen
import com.benidict.feature_product.details.ProductDetailsScreen
import com.benidict.feature_product.list.ProductListScreen
import com.benidict.feature_profile.ProfileScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    onMainScreen: (Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = HomeGraph) {
        homeNavGraph { navBackStackEntry, route ->
            onMainScreen(route == HomeScreenRoute)
            when (route) {
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
                    }, onLogin = {
                        navController.navigate(SignInScreenRoute)
                    }, onNavigateProfile = {
                        navController.navigate(ProfileScreenRoute)
                    })
                }
                ProfileScreenRoute -> {
                    ProfileScreen(navController)
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
                    val param: ProductListScreenRoute = navBackStackEntry.toRoute()
                    ProductListScreen(
                        navController,
                        categoryId = param.categoryId,
                        categoryName = param.categoryName
                    ) { productId ->
                        navController.navigate(ProductDetailsScreenRoute(productId = productId))
                    }
                }

                ProductDetailsScreenRoute -> {
                    val param: ProductDetailsScreenRoute =
                        navBackStackEntry.toRoute()
                    ProductDetailsScreen(navController, param.productId)
                }
            }
        }
        favoriteNavGraph { navBackStackEntry, route ->
            onMainScreen(route == FavoriteScreenRoute)
            when(route) {
                FavoriteScreenRoute -> {
                    FavoriteScreen(navController)
                }
            }
        }
        cartNavGraph { navBackStackEntry, route ->
            onMainScreen(route == CartScreenRoute)
            when(route) {
                CartScreenRoute -> {
                    CartScreen(navController)
                }
            }
        }
        notificationNavGraph { navBackStackEntry, route ->
            onMainScreen(route == NotificationScreenRoute)
            when(route) {
                NotificationScreenRoute -> {
                    NotificationListScreen(onNavigateToNotificationDetails = { id ->
                        navController.navigate(NotificationDetailsScreenRoute(id = id))
                    })
                }
                NotificationDetailsScreenRoute -> {
                    val param: NotificationDetailsScreenRoute =
                        navBackStackEntry.toRoute()
                    NotificationDetailsScreen(id = param.id, navController = navController)
                }
            }
        }
    }
}