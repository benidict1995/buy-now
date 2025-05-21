package com.benidict.feature_home.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.common_ui.banner.BannerPager
import com.benidict.common_ui.banner.SmallBannerView
import com.benidict.common_ui.filter.ProductFilterView
import com.benidict.common_ui.grid.ProductGridView
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.location.LocationHeaderView
import com.benidict.common_ui.location.LocationSelectorView
import com.benidict.common_ui.search.SearchFilterView
import com.benidict.common_ui.section.CategorySectionView
import com.benidict.common_ui.theme.GrayishWhite
import com.benidict.feature_home.home.model.HomeUiModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val homeUiModelList by viewModel.homeUiModel.collectAsState(emptyList())
    MainLayout(
        hasTopBar = true,
        hasBackButton = false,
        hasNextButton = false,
        containerColor = GrayishWhite
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            items(homeUiModelList) { section ->
                when (section) {
                    is HomeUiModel.LocationHeader -> LocationHeaderView(section.locationName)

                    is HomeUiModel.Spacer -> Spacer(
                        modifier = Modifier.height(section.spaceSize.dp)
                    )

                    is HomeUiModel.SearchFilterSection -> SearchFilterView()
                    is HomeUiModel.BannerPagerSection -> BannerPager(section.colors)
                    is HomeUiModel.CategorySection -> CategorySectionView(section.categories)
                    is HomeUiModel.ProductFilterSection -> ProductFilterView(section.filters)
                    is HomeUiModel.ProductGridSection -> ProductGridView(section.products)
                }
            }
        }
    }
}