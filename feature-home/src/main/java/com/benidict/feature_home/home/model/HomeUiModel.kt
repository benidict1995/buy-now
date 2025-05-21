package com.benidict.feature_home.home.model

import androidx.compose.ui.graphics.Color

sealed class HomeUiModel {
    data class LocationHeader(val locationName: String) : HomeUiModel()
    data class Spacer(val spaceSize: Int): HomeUiModel()
    object SearchFilterSection: HomeUiModel()
    data class BannerPagerSection(val colors: List<Color>): HomeUiModel()
    data class CategorySection(val categories: List<String>): HomeUiModel()
    data class ProductFilterSection(val filters: List<String>): HomeUiModel()
    data class ProductGridSection(val products: List<String>): HomeUiModel()
}