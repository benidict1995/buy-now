package com.benidict.feature_login.ui.password

sealed class EnterPasswordState {
    object NavigateToHome: EnterPasswordState()
    data class ShowError(val error: String): EnterPasswordState()
}