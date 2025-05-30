package com.benidict.buy_now

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.benidict.buy_now.navigation.BaseNavGraph
import com.benidict.buy_now.ui.theme.BuynowTheme
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
                BaseNavGraph(navController)

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuynowTheme {

    }
}