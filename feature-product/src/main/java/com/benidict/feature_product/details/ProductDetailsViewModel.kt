package com.benidict.feature_product.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.buy_now.cart.CartProduct
import com.benidict.buy_now.product.Product
import com.benidict.data.repository.cart.CartRepository
import com.benidict.data.repository.product.ProductRepository
import com.benidict.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _productState: MutableStateFlow<Product> = MutableStateFlow(Product())
    val productState = _productState.asStateFlow()

    private val _quantityState: MutableStateFlow<Int> = MutableStateFlow(1)
    val quantityState = _quantityState.asStateFlow()



    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            cartRepository.addToCart(
                CartProduct(
                    productId = productId,
                    quantity = quantity
                )
            )
        }
    }

    fun loadProductDetails(productId: Int) {
        viewModelScope.launch {
            try {
                val productQuantity = cartRepository.loadCartProductById(productId)?.quantity ?: 1
                _quantityState.value = productQuantity

                val product = productRepository.getProductById(productId)
                _productState.value = product
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}