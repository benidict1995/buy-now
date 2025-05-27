package com.benidict.buy_now.filter

import android.icu.text.CaseMap.Fold
import android.util.Log

data class Filter(
    val filterName: String = "",
    var isSelected: Boolean = false
) {
    companion object {

        private val productFilter = mutableListOf(
            Filter(
                filterName = ProductFilter.ALL.displayName,
                isSelected = true
            ), Filter(
                filterName = ProductFilter.POPULAR.displayName,
                isSelected = false
            ), Filter(
                filterName = ProductFilter.NEW.displayName,
                isSelected = false
            ), Filter(
                filterName = ProductFilter.PRICE_HIGH.displayName,
                isSelected = false
            ), Filter(
                filterName = ProductFilter.PRICE_LOW.displayName,
                isSelected = false
            )
        )

        fun filter(filterName: String): List<Filter> {
            val filtered = productFilter.mapIndexed { index, item ->
                productFilter[index].copy(isSelected = item.filterName == filterName)
            }

            return filtered
        }
    }
}