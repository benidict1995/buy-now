package com.benidict.common_ui.layout

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.common_ui.R
import com.benidict.common_ui.bottomnav.CustomBottomNavigationView
import com.benidict.common_ui.theme.GrayDisabled
import com.benidict.common_ui.theme.MainBackground
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    hasBackButton: Boolean = false,
    hasTopBar: Boolean = false,
    hasBottomBar: Boolean = false,
    hasNextButton: Boolean = false,
    enableNextButton: Boolean = false,
    errorMessage: String = "",
    titleTopBar: String = "",
    containerColor: Color = MainBackground,
    onResetErrorMessage: (() -> Unit)? = null,
    onBackPressed: (() -> Unit)? = null,
    onNextPressed: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            }
        },
        topBar = {
            if (hasTopBar) {
                CenterAlignedTopAppBar(
                    colors = topAppBarColors(
                        containerColor = containerColor,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(titleTopBar, fontWeight = FontWeight.Bold)
                    },
                    navigationIcon = {
                        if (hasBackButton) {
                            IconButton(onClick = { onBackPressed?.let { it() } }) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    },
                    actions = {
                        if (hasNextButton) {
                            IconButton(onClick = {
                                if (enableNextButton) {
                                    onNextPressed?.let {
                                        it()
                                    }
                                }
                            }) {
                                Text(
                                    text = "Next",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (enableNextButton)
                                    Color.Black else GrayDisabled
                                )
                            }
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (hasBottomBar) {
                CustomBottomNavigationView {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    //Spacer(Modifier.width(56.dp))
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
        LaunchedEffect(errorMessage.isNotEmpty()) {
            if (errorMessage.isNotEmpty()) {
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(
                        message = errorMessage,
                        duration = SnackbarDuration.Short
                    )
                    onResetErrorMessage?.invoke()
                }
            }
        }
    }
}