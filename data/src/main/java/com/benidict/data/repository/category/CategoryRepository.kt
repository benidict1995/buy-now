package com.benidict.data.repository.category

import com.benidict.buy_now.category.Category
import com.benidict.buy_now.source.category.CategoryRemoteSource
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryRemoteSource: CategoryRemoteSource
) {

    suspend fun getAllCategory(): List<Category> {
        return categoryRemoteSource.getCategories()
    }
}