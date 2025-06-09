package com.benidict.feature_product.list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.common_ui.grid.ProductGridView
import com.benidict.common_ui.icon.CircleBackButton
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite

@Composable
fun ProductListScreen(navController: NavHostController, categoryId: Int, categoryName: String, onNavigateToProductDetails: (Int) -> Unit) {
    val viewModel = hiltViewModel<ProductListViewModel>()
    val products = viewModel.productsState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadProductsByCategoryId(categoryId)
    }

    MainLayout(
        containerColor = GrayishWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CircleBackButton {
                    navController.popBackStack()
                }
                Text(text = categoryName, fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center))
            }
            ProductGridView(products.value, modifier = Modifier.fillMaxSize()
                .padding(bottom = 8.dp)) { productId ->
                onNavigateToProductDetails(productId)
            }
        }
    }

}