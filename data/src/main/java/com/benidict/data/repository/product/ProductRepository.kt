package com.benidict.data.repository.product

import com.benidict.buy_now.filter.ProductFilter
import com.benidict.buy_now.product.Product
import com.benidict.buy_now.source.product.ProductRemoteSource
import javax.inject.Inject

class ProductRepository @Inject constructor(val productRemoteSource: ProductRemoteSource) {

    suspend fun getProducts(filter: ProductFilter): List<Product> {
        val products = productRemoteSource.getProducts()
        return Product.productSortByFilter(filter = filter, products = products)
    }

    suspend fun getProductById(productId: Int): Product {
        return productRemoteSource.getProductById(productId)
    }

    suspend fun getProductsByCategoryId(categoryId: Int): List<Product> {
        return productRemoteSource.getProductsByCategory(categoryId)
    }
}