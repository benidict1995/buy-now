package com.benidict.buy_now.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreService constructor(val context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "buy_now_prefs")

    inline fun <reified T> readFromDataStore(key: String, default: T): Flow<T> {
        return context.dataStore.data.map { prefs ->
            when (default) {
                is String -> prefs[stringPreferencesKey(key)] as T? ?: default
                is Boolean -> prefs[booleanPreferencesKey(key)] as T? ?: default
                is Int -> prefs[intPreferencesKey(key)] as T? ?: default
                is Float -> prefs[floatPreferencesKey(key)] as T? ?: default
                is Long -> prefs[longPreferencesKey(key)] as T? ?: default
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    suspend inline fun <reified T> saveToDataStore(key: String, value: T) {
        context.dataStore.edit { prefs ->
            when (value) {
                is String -> prefs[stringPreferencesKey(key)] = value
                is Boolean -> prefs[booleanPreferencesKey(key)] = value
                is Int -> prefs[intPreferencesKey(key)] = value
                is Float -> prefs[floatPreferencesKey(key)] = value
                is Long -> prefs[longPreferencesKey(key)] = value
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }
}