package com.example.androidpractice.anime.presentation.model

data class AnimeListViewState(
    val state: State = State.Loading
) {
    sealed interface State {
        object Loading : State
        data class Error(val error: String) : State
        data class Success(val data: List<AnimeUiModel>) : State
    }
}