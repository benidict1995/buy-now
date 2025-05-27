package com.benidict.feature_home.home.model

import androidx.compose.ui.graphics.Color
import com.benidict.buy_now.category.Category
import com.benidict.buy_now.filter.Filter
import com.benidict.buy_now.product.Product

sealed class HomeUiModel {
    data class Spacer(val spaceSize: Int): HomeUiModel()
    object SearchFilterSection: HomeUiModel()
    data class BannerPagerSection(val colors: List<Color>): HomeUiModel()
    object CategorySection: HomeUiModel()
    object ProductFilterSection: HomeUiModel()
    object ProductGridSection: HomeUiModel()
}