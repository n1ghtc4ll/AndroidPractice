package com.example.androidpractice.anime_list.presentation.model

import com.example.androidpractice.anime_list.domain.model.AnimeFilter

data class AnimeListViewState(
    val state: State = State.Loading,
    val activeFilters: AnimeFilterModel = AnimeFilterModel(),
    val dialogFilters: AnimeFilterModel = AnimeFilterModel()
) {
    sealed interface State {
        object Loading : State
        data class Error(val error: String) : State
        data class Success(val data: List<AnimeUiModel>) : State
    }
}