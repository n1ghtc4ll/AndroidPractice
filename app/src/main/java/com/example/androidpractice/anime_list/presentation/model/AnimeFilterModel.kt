package com.example.androidpractice.anime_list.presentation.model

import com.example.androidpractice.anime_list.domain.model.AnimeFilter
import com.example.androidpractice.anime_list.domain.model.AnimeRating
import com.example.androidpractice.anime_list.domain.model.AnimeType

data class AnimeFilterModel(
    val type: AnimeType = AnimeType.ALL,
    val filter: AnimeFilter = AnimeFilter.ALL,
    val rating: AnimeRating = AnimeRating.ALL
)
