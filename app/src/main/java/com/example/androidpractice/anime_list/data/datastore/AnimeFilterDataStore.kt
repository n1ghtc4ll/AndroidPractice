package com.example.androidpractice.anime_list.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.filterDataStore by preferencesDataStore(name = "anime_filter_prefs")

class AnimeFilterDataStore(private val context: Context) {

    companion object {
        val TYPE_KEY = stringPreferencesKey("anime_type")
        val FILTER_KEY = stringPreferencesKey("anime_filter")
        val RATING_KEY = stringPreferencesKey("anime_rating")
    }

    val filterStringsFlow: Flow<Triple<String?, String?, String?>> = context.filterDataStore.data.map { prefs ->
        Triple(prefs[TYPE_KEY], prefs[FILTER_KEY], prefs[RATING_KEY])
    }

    suspend fun saveFilterStrings(type: String, filter: String, rating: String) {
        context.filterDataStore.edit { prefs ->
            prefs[TYPE_KEY] = type
            prefs[FILTER_KEY] = filter
            prefs[RATING_KEY] = rating
        }
    }
}