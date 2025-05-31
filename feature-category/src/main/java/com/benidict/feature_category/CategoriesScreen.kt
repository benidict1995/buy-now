package com.benidict.feature_category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.benidict.common_ui.grid.CategoryGridView
import com.benidict.common_ui.icon.CircleBackButton
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite

@Composable
fun CategoriesScreen(navController: NavHostController, onNavigateToProductList: (Int, String) -> Unit) {
    val viewModel = hiltViewModel<CategoriesViewModel>()
    val categories = viewModel.categoriesState.collectAsState()

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
                Text(text = "Categories", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center))
            }
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()

            ) {
                items(categories.value) { item ->
                    CategoryGridView(item, onClick = { categoryId, categoryName ->
                        onNavigateToProductList(categoryId, categoryName)
                    })
                }
            }
        }
    }
}