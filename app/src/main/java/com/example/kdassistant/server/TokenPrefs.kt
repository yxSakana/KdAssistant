package com.example.kdassistant.server

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

object TokenPrefs {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val TAG: String = "AuthPreferences"
    private val TOKEN_KEY = stringPreferencesKey("token")

    suspend fun saveToken(context: Context, v: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = v
        }
    }

    fun getTokenFlow(context: Context): Flow<String> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(TAG, "Error reading token preferences")
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[TOKEN_KEY] ?: ""
            }
}
