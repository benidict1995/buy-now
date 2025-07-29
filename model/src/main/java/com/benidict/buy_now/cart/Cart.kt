package com.benidict.buy_now.cart

data class Cart(
    val uid: String = "",
    val products: List<CartProduct> = emptyList()
) {
    companion object {
        fun empty() = Cart(
            uid = "",
            products = emptyList()
        )
    }
}