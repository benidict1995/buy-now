package com.benidict.common_ui.navigation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.rememberNavController
import com.benidict.common_ui.bottomnav.AppBottomNavigation
import com.benidict.common_ui.navigation.graph.MainNavGraph

@Composable
fun MainScreen(
    navRoute: @Composable (NavBackStackEntry, route: Any, graph: Any) -> Unit
){
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        MainNavGraph(navController = navController) { navBackStackEntry, route, graph ->
            navRoute(navBackStackEntry, route, graph)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AppBottomNavigation(navController)
        }
    }
}