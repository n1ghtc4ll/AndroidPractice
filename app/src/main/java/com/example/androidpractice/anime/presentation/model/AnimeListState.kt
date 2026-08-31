package com.example.androidpractice.anime.presentation.model

sealed interface AnimeListState {
    object Loading : AnimeListState
    object Empty : AnimeListState
    data class Error(val error: String) : AnimeListState
    data class Success(val data: List<AnimeUiModel>) : AnimeListState
}