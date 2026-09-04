package com.example.androidpractice.anime.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


class AnimeFilterDataStore(private val context: Context) {
    private val Context.dataStore by preferencesDataStore("filters")
    val data = context.dataStore.data

    val mainFilterFlow = data.map { it[MAIN_FILTER_KEY] }
    val releaseTypeFlow = data.map { it[RELEASE_TYPE_KEY] }
    val ageRatingFlow = data.map { it[AGE_RATING_KEY] }

    private companion object {
        val MAIN_FILTER_KEY = stringPreferencesKey("api_category")
        val RELEASE_TYPE_KEY = stringPreferencesKey("api_release_type")
        val AGE_RATING_KEY = stringPreferencesKey("api_age_rating")
    }

    suspend fun saveFilters(
        mainFilterApi: String?,
        releaseTypeApi: String?,
        ageRatingApi: String?
    ) {
        context.dataStore.edit { prefs ->
            mainFilterApi?.let { prefs[MAIN_FILTER_KEY] = it } ?: prefs.remove(MAIN_FILTER_KEY)
            releaseTypeApi?.let { prefs[RELEASE_TYPE_KEY] = it } ?: prefs.remove(RELEASE_TYPE_KEY)
            ageRatingApi?.let { prefs[AGE_RATING_KEY] = it } ?: prefs.remove(AGE_RATING_KEY)
        }
    }
}