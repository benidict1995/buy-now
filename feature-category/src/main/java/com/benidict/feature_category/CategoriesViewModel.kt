package com.benidict.feature_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.buy_now.category.Category
import com.benidict.data.repository.category.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel(){

    private val _categoriesState: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categoriesState = _categoriesState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            try {
                val categories = categoryRepository.getAllCategory()
                _categoriesState.value = categories
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}