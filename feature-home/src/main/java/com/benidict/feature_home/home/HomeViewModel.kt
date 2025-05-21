package com.benidict.feature_home.home

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.feature_home.home.model.HomeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val colors = listOf(Color.Cyan, Color.Red, Color.Blue, Color.Green)
    private val productFilter = listOf("All", "Popular", "New", "Price High", "Price Low")
    private val categories = listOf("Dog", "Cat", "Rat", "Chicken", "Bird", "Snake", "Tiger")
    private val products = listOf("Apple", "Orange", "Grapes")

    private val _homeUiModel: MutableStateFlow<List<HomeUiModel>> = MutableStateFlow(emptyList())
    val homeUiModel = _homeUiModel.asSharedFlow()

    init {
        renderHomeSections()
    }
    private fun renderHomeSections() {
        viewModelScope.launch {
            val uiList = buildList<HomeUiModel> {
                add(HomeUiModel.LocationHeader("Tanza, Cavite"))
                add(HomeUiModel.Spacer(10))
                add(HomeUiModel.SearchFilterSection)
                add(HomeUiModel.Spacer(20))
                add(HomeUiModel.BannerPagerSection(colors))
                add(HomeUiModel.Spacer(20))
                add(HomeUiModel.CategorySection(categories))
                add(HomeUiModel.Spacer(30))
                add(HomeUiModel.ProductFilterSection(productFilter))
                add(HomeUiModel.Spacer(8))
                add(HomeUiModel.ProductGridSection(products))
            }
            _homeUiModel.value = uiList
        }
    }
}