package com.benidict.feature_home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.buy_now.banner.Banner
import com.benidict.buy_now.category.Category
import com.benidict.buy_now.filter.Filter
import com.benidict.buy_now.filter.ProductFilter
import com.benidict.buy_now.product.Product
import com.benidict.data.repository.banner.BannerRepository
import com.benidict.data.repository.category.CategoryRepository
import com.benidict.data.repository.product.ProductRepository
import com.benidict.feature_home.home.model.HomeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val bannerRepository: BannerRepository
): ViewModel() {
    private val _productFilterState: MutableStateFlow<List<Filter>> = MutableStateFlow(Filter.filter(ProductFilter.ALL.displayName))
    val productFilterState = _productFilterState.asStateFlow()

    private val _categoriesState: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categoriesState = _categoriesState.asStateFlow()

    private val _productsState: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val productsState = _productsState.asStateFlow()

    private val _bannersState: MutableStateFlow<List<Banner>> = MutableStateFlow(emptyList())
    val bannersState = _bannersState.asStateFlow()

    private val _homeUiModel: MutableStateFlow<List<HomeUiModel>> = MutableStateFlow(emptyList())
    val homeUiModel = _homeUiModel.asSharedFlow()

    private val _locationNameState: MutableStateFlow<String> = MutableStateFlow("Tanza, Cavite")
    val locationNameState = _locationNameState.asStateFlow()

    init {
        loadHomeSectionData()
    }

    fun filterProducts(displayName: String) {
        viewModelScope.launch {
            try {
                _productFilterState.value = Filter.filter(filterName = displayName)

                val filterProducts = productRepository.getProducts(ProductFilter.entries.find { it.displayName == displayName }?: ProductFilter.ALL)
                _productsState.value = filterProducts
            } catch (e: Exception) {
                Log.d("makerChecker", "error-filterProducts:${e.message}")
            }
        }
    }

    private fun loadHomeSectionData() {
        viewModelScope.launch {
            try {
                val resultCategories = async { categoryRepository.getAllCategory() }
                val resultProducts = async { productRepository.getProducts(ProductFilter.ALL) }
                val resultBanners = async { bannerRepository.getBanners() }

                _bannersState.value = resultBanners.await()
                _categoriesState.value = resultCategories.await()
                _productsState.value = resultProducts.await()
                renderHomeSections()
            } catch (e: Exception) {
                Log.d("makerChecker", "error-loadproducts:${e.message}")
            }
        }
    }


    private fun renderHomeSections() {
        viewModelScope.launch {
            val uiList = buildList<HomeUiModel> {
                add(HomeUiModel.Spacer(10))
                add(HomeUiModel.SearchFilterSection)
                add(HomeUiModel.Spacer(30))
                add(HomeUiModel.BannerPagerSection)
                add(HomeUiModel.Spacer(20))
                add(HomeUiModel.CategorySection)
                add(HomeUiModel.Spacer(30))
                add(HomeUiModel.ProductFilterSection)
                add(HomeUiModel.Spacer(8))
                add(HomeUiModel.ProductGridSection)
            }
            _homeUiModel.value = uiList
        }
    }
}