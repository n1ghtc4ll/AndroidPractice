package com.example.androidpractice.anime.presentation.model

import com.example.androidpractice.anime.domain.model.Anime

data class AnimeUiModel(
    val id: Int,
    val title: String,
    val url: String,
    val imageUrl: String?,
    val type: String,
    val genres: String,
    val episodes: String,
    val score: String,
    val description: String,
    val domainModel: Anime
)