package com.benidict.common_ui.navigation.route

import kotlinx.serialization.Serializable


@Serializable
object LandingRoute

@Serializable
object SignInRoute

@Serializable
data class UserDetailsFormRoute(val email: String)

@Serializable
object HomeRoute

@Serializable
data class EnterPasswordRoute(val email: String)