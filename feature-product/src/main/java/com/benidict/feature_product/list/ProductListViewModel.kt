package com.benidict.feature_product.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.buy_now.product.Product
import com.benidict.data.repository.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productsState: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val productsState = _productsState.asStateFlow()

    fun loadProductsByCategoryId(categoryId: Int) {
        viewModelScope.launch {
            try {
                val products = productRepository.getProductsByCategoryId(categoryId)
                _productsState.value = products
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}