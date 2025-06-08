package com.benidict.buy_now.user

data class UserRequest(
    val email: String = "",
    val firstname: String = "",
    val lastname: String = "",
    val password: String = "",
    val uid: String = ""
) {
    companion object {
        fun UserRequest.toUserMap() = mapOf(
            "uid" to this.uid,
            "firstname" to this.firstname,
            "lastname" to this.lastname,
            "email" to this.email
        )
    }
}