package com.example.androidpractice.anime_list.data.mapper

import com.example.androidpractice.anime_list.domain.model.AnimeFilter
import com.example.androidpractice.anime_list.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime_list.domain.model.AnimeRating
import com.example.androidpractice.anime_list.domain.model.AnimeType

class FilterSettingsMapper {
    fun mapToDomain(typeStr: String?, filterStr: String?, ratingStr: String?): AnimeFilterSettings {
        return AnimeFilterSettings(
            type = runCatching { AnimeType.valueOf(typeStr.orEmpty()) }.getOrDefault(AnimeType.ALL),
            filter = runCatching { AnimeFilter.valueOf(filterStr.orEmpty()) }.getOrDefault(AnimeFilter.ALL),
            rating = runCatching { AnimeRating.valueOf(ratingStr.orEmpty()) }.getOrDefault(AnimeRating.ALL)
        )
    }
}