package com.benidict.feature_cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.common_ui.icon.CircleBackButton
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite

@Composable
fun CartScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<CartViewModel>()

    val cart = viewModel.cartState.collectAsState()
    MainLayout(
        containerColor = GrayishWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .systemBarsPadding()
                .padding(bottom = 46.dp)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "Cart", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 12.dp))

                if (cart.value.products.isEmpty()) {
                    Text(text = "Empty Cart", fontSize = 14.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center).padding(top = 12.dp))
                }
            }

        }
    }
}