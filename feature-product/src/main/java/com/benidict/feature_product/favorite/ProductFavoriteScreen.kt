package com.benidict.feature_product.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.benidict.common_ui.icon.CircleBackButton
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite

@Composable
fun ProductFavoriteScreen(navController: NavHostController, onNavigateToProductDetails: (Int) -> Unit) {
    MainLayout (
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
            Box(modifier = Modifier.fillMaxWidth()) {
                CircleBackButton {
                    navController.popBackStack()
                }
                Text(text = "Favorites", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center))
            }
//            ProductGridView(products.value, modifier = Modifier.fillMaxSize()
//                .padding(bottom = 8.dp)) { productId ->
//                onNavigateToProductDetails(productId)
//            }
        }

    }
}