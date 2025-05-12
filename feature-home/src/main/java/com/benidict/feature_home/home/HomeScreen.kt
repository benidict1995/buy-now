package com.benidict.feature_home.home

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.benidict.common_ui.banner.BannerPager
import com.benidict.common_ui.banner.SmallBannerView
import com.benidict.common_ui.filter.ProductFilterView
import com.benidict.common_ui.grid.ProductGridView
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.location.LocationSelectorView
import com.benidict.common_ui.search.SearchFilterView
import com.benidict.common_ui.section.CategorySectionView
import com.benidict.common_ui.theme.GrayishWhite

@Composable
fun HomeScreen(navController: NavHostController) {
    val colors = listOf(Color.Cyan, Color.Red, Color.Blue, Color.Green)
    val productFilter = listOf("All", "Popular", "New", "Price High", "Price Low")
    val categories = listOf("Dog", "Cat", "Rat", "Chicken", "Bird", "Snake", "Tiger")
    val products = listOf("Apple", "Orange", "Grapes")
    val scrollState = rememberScrollState()
    MainLayout(
        hasTopBar = true,
        hasBackButton = false,
        hasNextButton = false,
        containerColor = GrayishWhite
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(com.benidict.common_ui.R.drawable.baseline_account_circle_24),
                    contentDescription = ""
                )
                LocationSelectorView(
                    locationName = "Tanza, Cavite"
                )
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(com.benidict.common_ui.R.drawable.baseline_notifications_none_24),
                    contentDescription = ""
                )
            }
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            SearchFilterView()
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            BannerPager(
                items = colors
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            CategorySectionView(categories)
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            ProductFilterView(productFilter)
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            ProductGridView(products)
        }
    }
}