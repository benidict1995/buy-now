package com.benidict.buy_now.filter

enum class ProductFilter(val displayName: String) {
    ALL("All"),
    POPULAR("Popular"),
    NEW("New"),
    PRICE_HIGH("Price: High to Low"),
    PRICE_LOW("Price: Low to High")
}