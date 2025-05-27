package com.benidict.common_utils.transform

import java.text.NumberFormat
import java.util.Locale

fun Int.convertToPeso(): String {
    val pesoFormat = NumberFormat.getCurrencyInstance(Locale("en", "PH")).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    val formatted = pesoFormat.format(this.toDouble())
    return formatted
}