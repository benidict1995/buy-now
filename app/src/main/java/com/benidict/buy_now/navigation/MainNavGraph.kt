package com.benidict.buy_now.navigation

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
import com.benidict.common_ui.navigation.route.FavoriteGraph
import com.benidict.common_ui.navigation.route.FavoriteScreenRoute
import com.benidict.common_ui.navigation.route.HomeGraph
import com.benidict.common_ui.navigation.route.HomeScreenRoute
import com.benidict.common_ui.navigation.route.NotificationScreenRoute
import com.benidict.common_ui.navigation.route.ProductDetailsScreenRoute
import com.benidict.common_ui.navigation.route.ProductListScreenRoute
import com.benidict.common_ui.navigation.route.ViewAllCategoryScreenRoute
import com.benidict.feature_category.CategoriesScreen
import com.benidict.feature_home.home.HomeScreen
import com.benidict.feature_product.details.ProductDetailsScreen
import com.benidict.feature_product.list.ProductListScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = HomeGraph) {
        homeNavGraph { navBackStackEntry, route ->
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
            when(route) {
                FavoriteScreenRoute -> {
                    DefaultScreen("Favorite Screen")
                }
            }
        }
        cartNavGraph { navBackStackEntry, route ->
            when(route) {
                CartScreenRoute -> {
                    DefaultScreen("Cart Screen")
                }
            }
        }
        notificationNavGraph { navBackStackEntry, route ->
            when(route) {
                NotificationScreenRoute -> {
                    DefaultScreen("Notification Screen")
                }
            }
        }
    }
}