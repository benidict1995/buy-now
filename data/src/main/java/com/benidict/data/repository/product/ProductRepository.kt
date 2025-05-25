package com.benidict.data.repository.product

import com.benidict.buy_now.product.Product
import com.benidict.buy_now.source.product.ProductRemoteSource
import javax.inject.Inject

class ProductRepository @Inject constructor(val productRemoteSource: ProductRemoteSource) {

    suspend fun getAllProducts(): List<Product> {
        return productRemoteSource.getProductsByCategory("")
    }
}