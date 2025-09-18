package com.benidict.feature_cart

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.buy_now.cart.Cart
import com.benidict.data.repository.cart.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
): ViewModel() {

    private val _cartState: MutableStateFlow<Cart> = MutableStateFlow(Cart.empty())
    val cartState = _cartState.asStateFlow()

    init {
       loadCart()
    }

    private fun loadCart() {
        viewModelScope.launch {
            val cart = cartRepository.loadCart()
            Log.d("makerChecker", "cart-screen:$cart")
            _cartState.value = cart
        }
    }
}