package com.benidict.data.repository.cart

import android.util.Log
import com.benidict.buy_now.cart.Cart
import com.benidict.buy_now.cart.CartProduct
import com.benidict.buy_now.product.Product
import com.benidict.buy_now.source.cart.CartRemoteSource
import com.benidict.buy_now.source.user.UserLocalSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val userLocalSource: UserLocalSource,
    private val cartRemoteSource: CartRemoteSource
) {

    suspend fun addToCart(cartProduct: CartProduct) {
        val uid = userLocalSource.loadUser().first().uid
        val userCart = cartRemoteSource.loadCart(uid)

        val updatedProducts = if (userCart.products.isNotEmpty()) {
            val products = userCart.products.toMutableList()
            val index = products.indexOfFirst { it.productId == cartProduct.productId }

            if (index != -1) {
                products[index] = products[index].copy(quantity = cartProduct.quantity)
            } else {
                products.add(cartProduct)
            }

            products
        } else {
            listOf(cartProduct)
        }

        val cart = Cart(
            uid = uid,
            products = updatedProducts
        )

        cartRemoteSource.addToCart(cart)
    }

    suspend fun loadCartProductById(productId: Int): CartProduct {
        val uid = userLocalSource.loadUser().first().uid
        val cart = cartRemoteSource.loadCart(uid).products.find { it.productId == productId }
        val localCart = userLocalSource.getUserCart()
        //  Log.d("makerChecker", "cart:$cart\nlocalCart:$localCart")
        return CartProduct(productId = productId, quantity = 1)
    }

    suspend fun loadCart(): Cart {
        val user = userLocalSource.loadUser().first()
        val remoteCart = cartRemoteSource.loadCart(user.uid)
        val localCart = userLocalSource.getUserCart()

        return if (remoteCart != localCart) {
            userLocalSource.saveUserCart(remoteCart)
            remoteCart
        } else {
            localCart
        }
    }
}