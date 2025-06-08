package com.benidict.buy_now.service.key

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    const val FIRST_NAME = "user_first_name"
    const val LAST_NAME = "user_last_name"
    const val EMAIL = "user_email"
    const val UID = "user_uid"
    const val LOGGED_IN = "user_logged_in"
}