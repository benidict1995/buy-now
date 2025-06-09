package com.benidict.feature_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.common_ui.icon.CircleClose
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite

@Composable
fun ProfileScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val userDetails = viewModel.userDetailsState.collectAsState()
    MainLayout(
        containerColor = GrayishWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .systemBarsPadding()
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CircleClose {
                    navController.popBackStack()
                }
                Text(
                    text = "Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}