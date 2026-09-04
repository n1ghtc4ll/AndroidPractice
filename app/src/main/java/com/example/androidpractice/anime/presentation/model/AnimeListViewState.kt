package com.example.androidpractice.anime.presentation.model

import com.example.androidpractice.anime.domain.model.AnimeFilterSettings

data class AnimeListViewState(
    val state: AnimeListState = AnimeListState.Loading,
    val filters: AnimeFilterSettings = AnimeFilterSettings()
)