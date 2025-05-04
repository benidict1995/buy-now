package com.benidict.feature_login.ui.signin

sealed class SignInState {
    object NavigateToPassword: SignInState()
    object NavigateToSignUp: SignInState()
}