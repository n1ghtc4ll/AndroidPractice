package com.example.androidpractice.anime_list.presentation.model

data class AnimeDetailsViewState(
    val anime: AnimeUiModel,
    val isFavourite: Boolean = false
)