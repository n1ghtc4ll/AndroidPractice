package com.example.androidpractice.anime.presentation.model

data class AnimeDetailsViewState(
    val anime: AnimeUiModel,
    val isFavourite: Boolean = false
)