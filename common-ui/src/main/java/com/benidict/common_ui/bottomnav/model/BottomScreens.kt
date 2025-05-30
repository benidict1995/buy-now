package com.benidict.common_ui.bottomnav.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.ui.graphics.vector.ImageVector
import com.benidict.common_ui.navigation.route.CartGraph
import com.benidict.common_ui.navigation.route.FavoriteGraph
import com.benidict.common_ui.navigation.route.HomeGraph
import com.benidict.common_ui.navigation.route.NotificationGraph
import kotlinx.serialization.Serializable

sealed class BottomScreens<T>(
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: T
) {

    data object Home : BottomScreens<HomeGraph>(
        name = "Home",
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        route = HomeGraph
    )

    data object Favorite : BottomScreens<FavoriteGraph>(
        name = "Favorite",
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Outlined.Favorite,
        route = FavoriteGraph
    )

    data object Cart : BottomScreens<CartGraph>(
        name = "Cart",
        unselectedIcon = Icons.Outlined.ShoppingBasket,
        selectedIcon = Icons.Filled.ShoppingBasket,
        route = CartGraph
    )

    data object Notification : BottomScreens<NotificationGraph>(
        name = "Notification",
        unselectedIcon = Icons.Outlined.Notifications,
        selectedIcon = Icons.Filled.Notifications,
        route = NotificationGraph
    )

}