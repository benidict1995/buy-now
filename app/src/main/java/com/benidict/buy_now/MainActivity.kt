package com.benidict.buy_now

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.benidict.buy_now.ui.theme.BuynowTheme
import com.benidict.common_ui.navigation.graph.MainGraph
import com.benidict.common_ui.navigation.route.HomeRoute
import com.benidict.common_ui.navigation.route.LandingRoute
import com.benidict.common_ui.navigation.route.SignInRoute
import com.benidict.common_ui.navigation.route.UserDetailsFormRoute
import com.benidict.feature_home.home.HomeScreen
import com.benidict.feature_login.ui.landing.LandingScreen
import com.benidict.feature_login.ui.signin.SignInScreen
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
                            })
                        }

                        SignInRoute -> {
                            SignInScreen(navController, onNext = {
                                navController.navigate(UserDetailsFormRoute)
                            })
                        }

                        UserDetailsFormRoute -> {
                            UserDetailsFormScreen(navController, onNext = {
                                navController.navigate(HomeRoute)
                            })
                        }

                        HomeRoute -> {
                            HomeScreen(navController)
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