package com.benidict.feature_product.details

import android.util.Log
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
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel(){
    private val _productState: MutableStateFlow<Product> = MutableStateFlow(Product())
    val productState = _productState.asStateFlow()

    fun loadProductDetails(productId: Int) {
        viewModelScope.launch {
            try {
                val product = productRepository.getProductById(productId)
                _productState.value = product
            } catch (e: Exception) {
                Log.d("makerChecker", "productDetailsError:${e.message}")
            }
        }
    }
}