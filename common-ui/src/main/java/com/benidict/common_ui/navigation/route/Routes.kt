package com.benidict.common_ui.navigation.route

import kotlinx.serialization.Serializable

@Serializable
object CartGraph
@Serializable
object FavoriteGraph
@Serializable
object HomeGraph
@Serializable
object LandingGraph
@Serializable
object NotificationGraph
@Serializable
object MainGraph

@Serializable
object MainRoute

@Serializable
object LandingScreenRoute

@Serializable
object SignInScreenRoute

@Serializable
data class UserDetailsFormScreenRoute(val email: String)

@Serializable
data class EnterPasswordScreenRoute(val email: String)

@Serializable
object CartScreenRoute

@Serializable
object FavoriteScreenRoute

@Serializable
object NotificationScreenRoute

@Serializable
data class NotificationDetailsScreenRoute(val id: Int)

@Serializable
object HomeScreenRoute

@Serializable
object ViewAllCategoryScreenRoute

@Serializable
data class ProductDetailsScreenRoute(val productId: Int)

@Serializable
data class ProductListScreenRoute(val categoryId: Int, val categoryName: String)


@Serializable
object LandingRoute

@Serializable
object SignInRoute

@Serializable
data class UserDetailsFormRoute(val email: String)

@Serializable
object HomeRoute

@Serializable
data class EnterPasswordRoute(val email: String)

@Serializable
object NotificationListRoute

@Serializable
object ViewAllCategoryRoute

@Serializable
data class ProductDetailsRoute(val productId: Int)

@Serializable
data class ProductListRoute(val categoryId: Int, val categoryName: String)