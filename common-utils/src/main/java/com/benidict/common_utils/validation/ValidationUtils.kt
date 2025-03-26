package com.benidict.common_utils.validation

import android.util.Log

fun isPhoneValid(number: String): Boolean {
    return (number.take(1) == "9" && number.length == 10)
}