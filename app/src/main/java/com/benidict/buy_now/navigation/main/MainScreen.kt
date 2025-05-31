package com.benidict.buy_now.navigation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.benidict.buy_now.navigation.MainNavGraph
import com.benidict.common_ui.bottomnav.AppBottomNavigation

@Composable
fun MainScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        val isMainScreen = remember {
            mutableStateOf(true)
        }
        MainNavGraph(navController = navController) { mainScreen ->
            isMainScreen.value = mainScreen
        }
        //   .systemBarsPadding()
        if (isMainScreen.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                AppBottomNavigation(navController)
            }
        }
    }
}