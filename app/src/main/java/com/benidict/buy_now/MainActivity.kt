package com.benidict.buy_now

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.benidict.buy_now.navigation.BaseNavGraph
import com.benidict.buy_now.ui.theme.BuynowTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isUserLoggedIn = remember { mutableStateOf(false) }
            val isApiCallTrigger = remember { mutableStateOf(false) }
            val navController = rememberNavController()
            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    viewModel.isUserLoggedIn.collectLatest {
                        isUserLoggedIn.value = it
                        isApiCallTrigger.value = true
                    }
                }
            }
            BuynowTheme {
                if (isApiCallTrigger.value) {
                    BaseNavGraph(navController, isUserLoggedIn = isUserLoggedIn.value)
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