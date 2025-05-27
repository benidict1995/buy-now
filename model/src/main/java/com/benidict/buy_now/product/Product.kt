package com.benidict.buy_now.product

import android.util.Log
import androidx.annotation.Keep
import com.benidict.buy_now.filter.ProductFilter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ktx.getField

data class Product(
    val productId: Int = 0,
    val categoryId: Int = 0,
    val productName: String = "",
    val productDescription: String = "",
    val productImageUrl: String = "",
    val weight: String = "",
    val price: Int = 0,
    val quantity: Int = 0,
    var isNew: Boolean = false,
    var isHot: Boolean = false
) {
    companion object {
        fun productSortByFilter(filter: ProductFilter, products: List<Product>): List<Product> {
            return when (filter) {
                ProductFilter.ALL -> products
                ProductFilter.NEW -> products.filter { it.isNew }
                ProductFilter.PRICE_HIGH -> products.sortedByDescending { it.price }
                ProductFilter.PRICE_LOW -> products.sortedBy { it.price }
                ProductFilter.POPULAR -> products.filter { it.isHot }
            }
        }

        fun mapProductDTO(documents: List<DocumentSnapshot>): List<Product> {
            val productList: MutableList<Product> = mutableListOf()
            documents.forEach { doc ->
                productList.add(
                    Product(
                        productId = doc.getField<Int>("productId") ?: 0,
                        categoryId = doc.getField<Int>("categoryId") ?: 0,
                        productName = doc.getString("productName") ?: "",
                        productDescription = doc.getString("productDescription") ?: "",
                        weight = doc.getString("weight") ?: "",
                        price = doc.getField<Int>("price") ?: 0,
                        quantity = doc.getField<Int>("quantity") ?: 0,
                        isNew = doc.getBoolean("isNew") ?: false,
                        isHot = doc.getBoolean("isHot") ?: false
                    )
                )
            }
            return productList.toList()
        }

    }
}