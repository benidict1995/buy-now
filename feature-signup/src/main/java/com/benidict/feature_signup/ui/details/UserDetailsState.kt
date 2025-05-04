package com.benidict.feature_signup.ui.details

sealed class UserDetailsState{
    object NavigateToHome: UserDetailsState()
    data class ShowError(val message: String): UserDetailsState()
}