package com.example.androidpractice.anime.presentation.model

import com.example.androidpractice.anime.domain.model.AnimeFilterSettings

data class AnimeListViewState(
    val state: State = State.Loading,
    val filters: AnimeFilterSettings = AnimeFilterSettings()
) {
    sealed interface State {
        object Loading : State
        object Empty : State
        data class Error(val error: String) : State
        data class Success(val data: List<AnimeUiModel>) : State
    }
}