package com.benidict.feature_home.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.common_ui.banner.BannerPager
import com.benidict.common_ui.dialog.SimpleAlertDialog
import com.benidict.common_ui.filter.ProductFilterView
import com.benidict.common_ui.grid.ProductGridView
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.location.LocationHeaderView
import com.benidict.common_ui.search.SearchFilterView
import com.benidict.common_ui.section.CategorySectionView
import com.benidict.common_ui.theme.GrayishWhite
import com.benidict.feature_home.home.model.HomeUiModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    navController: NavHostController,
    onLogin: () -> Unit,
    onViewAllCategories: () -> Unit,
    onNavigateProductDetails: (Int) -> Unit,
    onNavigateProductByCategory: (Int, String) -> Unit,
    onNavigateProfile: () -> Unit
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val homeUiModelList by viewModel.homeUiModel.collectAsState(emptyList())
    val locationName by viewModel.locationNameState.collectAsState("")
    val products by viewModel.productsState.collectAsState()
    val productFilter by viewModel.productFilterState.collectAsState()
    val categories by viewModel.categoriesState.collectAsState()
    val banners by viewModel.bannersState.collectAsState()
    val selectedProductFilter = remember { mutableStateOf("") }

    var showUserLoggedInDialog by remember { mutableStateOf(false) }

    val savedStateHandle = remember { navController.currentBackStackEntry?.savedStateHandle }

    // Listen for result
    LaunchedEffect(savedStateHandle) {
        savedStateHandle?.getLiveData<Boolean>("refresh")?.observeForever { refresh ->
            if (refresh == true) {
                viewModel.filterProducts(selectedProductFilter.value) // Trigger your API refresh
                savedStateHandle.remove<Boolean>("refresh") // Prevent repeated calls
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isLoggedIn.collectLatest { isLoggedIn ->
            //showUserLoggedInDialog = isLoggedIn.not()
            showUserLoggedInDialog = true
            if (isLoggedIn) {
             //   onNavigateProfile()
            }
        }
    }

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
            LocationHeaderView(locationName, onNavigateToProfile = {
                viewModel.navigateToProfile()
            })
            Spacer(
                modifier = Modifier.height(10.dp)
            )

            SimpleAlertDialog(showUserLoggedInDialog,
                title = "Login Required",
                description = "Please log in to continue.",
                confirmButtonText = "Login",
                dismissButtonText = "Cancel",
                onDismiss = {
                    showUserLoggedInDialog = false
                }, onConfirm = {
                    showUserLoggedInDialog = false
                    onLogin()
                })

            LazyColumn {
                items(homeUiModelList) { section ->
                    when (section) {
                        is HomeUiModel.Spacer -> Spacer(
                            modifier = Modifier.height(section.spaceSize.dp)
                        )

                        is HomeUiModel.SearchFilterSection -> SearchFilterView()
                        is HomeUiModel.BannerPagerSection -> BannerPager(banners)
                        is HomeUiModel.CategorySection -> CategorySectionView(
                            categories,
                            onNavigateProductByCategory = { categoryId, categoryName ->
                                onNavigateProductByCategory(categoryId, categoryName)
                            },
                            onViewAll = {
                                onViewAllCategories()
                            })

                        is HomeUiModel.ProductFilterSection -> ProductFilterView(productFilter) {
                            selectedProductFilter.value = it
                            viewModel.filterProducts(it)
                        }

                        is HomeUiModel.ProductGridSection -> ProductGridView(
                            products, modifier = Modifier
                                .height(600.dp)
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) { productId ->
                            onNavigateProductDetails(productId)
                        }
                    }
                }
            }
        }
    }
}