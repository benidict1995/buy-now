package com.benidict.buy_now.product

data class Product(
    val id: Int = 0,
    val categoryId: Int = 0,
    val productName: String = "",
    val productDescription: String = "",
    val productImageUrl: String = "",
    val weight: String = "",
    val price: Int = 0,
    val quantity: Int = 0
)