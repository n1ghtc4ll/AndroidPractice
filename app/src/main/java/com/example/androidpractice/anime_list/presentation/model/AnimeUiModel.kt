package com.example.androidpractice.anime_list.presentation.model

data class AnimeUiModel(
    val id: String,
    val title: String,
    val url: String,
    val imageUrl: String?,
    val type: String,
    val genres: String,
    val episodes: String,
    val score: String,
    val description: String
)