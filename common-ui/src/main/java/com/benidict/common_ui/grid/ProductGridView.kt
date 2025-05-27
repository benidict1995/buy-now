package com.benidict.common_ui.grid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.benidict.buy_now.product.Product
import com.benidict.common_ui.product.ProductCardView

@Composable
fun ProductGridView(items: List<Product>, onNavigateProductDetails: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .height(600.dp)
            .fillMaxWidth()
            .padding(bottom = 8.dp)

    ) {
        LazyVerticalGrid(
            userScrollEnabled = true,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()

        ) {
            items(items) { item ->
                ProductCardView(item, modifier = Modifier.animateItem(), onClick = { productId ->
                    onNavigateProductDetails(productId)
                })
            }
        }
    }
}