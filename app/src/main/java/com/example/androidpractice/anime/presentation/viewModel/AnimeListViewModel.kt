package com.example.androidpractice.anime.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime.presentation.mapper.toUiModels
import com.example.androidpractice.anime.presentation.model.AnimeListState
import com.example.androidpractice.anime.presentation.model.AnimeListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val interactor: AnimeInteractor
): ViewModel() {
    private val mutableState = MutableStateFlow(AnimeListViewState())
    val viewState = mutableState.asStateFlow()

    init {
        loadAnimeList()
    }

    private fun loadAnimeList() {
        viewModelScope.launch {
            interactor.getFilters().collectLatest { activeFilters ->
                mutableState.update {
                    it.copy(state = AnimeListState.Loading, filters = activeFilters)
                }
            }

            try {
                val animeList = interactor.getAnimeTopList()
                updateState(AnimeListState.Success(animeList.toUiModels()))
            }
            catch (e: Exception) {
                updateState(AnimeListState.Error(e.localizedMessage ?: ""))
            }
        }
    }

    suspend fun updateFilters(filters: AnimeFilterSettings) {
        interactor.saveFilters(filters)
        mutableState.update { it.copy(filters = filters) }
    }

    fun onRetryClick() {
        loadAnimeList()
    }

    fun updateState(state: AnimeListState) = mutableState.update { it.copy(state = state) }
}