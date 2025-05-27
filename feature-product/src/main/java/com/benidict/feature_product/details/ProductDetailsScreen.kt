package com.benidict.feature_product.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.common_ui.icon.CircleBackButton
import com.benidict.common_ui.image.ImageLoader
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite
import com.benidict.common_utils.transform.convertToPeso

@Composable
fun ProductDetailsScreen(navController: NavHostController, productId: Int) {
    val viewModel = hiltViewModel<ProductDetailsViewModel>()
    val product = viewModel.productState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadProductDetails(productId)
    }
    MainLayout(
        hasBottomBar = false,
        hasTopBar = true,
        hasBackButton = false,
        hasNextButton = false,
        containerColor = GrayishWhite
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()

        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                CircleBackButton {
                    navController.popBackStack()
                }
            }
            ImageLoader(
                url = product.value.productImageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Text(
                text = product.value.productName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = product.value.weight,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.value.price.convertToPeso(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

        }
    }
}