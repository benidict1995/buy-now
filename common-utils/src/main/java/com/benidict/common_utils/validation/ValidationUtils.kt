package com.benidict.common_utils.validation

import android.util.Log

fun isPhoneValid(number: String): Boolean {
    return (number.take(1) == "9" && number.length == 10)
}

fun isFieldsEmpty(list: List<String>): Boolean {
    return list.contains("")
}

fun isEmailValid(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(emailRegex)
}