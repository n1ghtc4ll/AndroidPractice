package com.example.androidpractice.anime.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime.presentation.mapper.toUiModels
import com.example.androidpractice.anime.presentation.model.AnimeListState
import com.example.androidpractice.anime.presentation.model.AnimeListViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeMainListViewModel(
    private val interactor: AnimeInteractor
): ViewModel() {
    private val mutableState = MutableStateFlow(AnimeListViewState())
    val viewState = mutableState.asStateFlow()

    init {
        loadAnimeList()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadAnimeList() {
        viewModelScope.launch {
            updateState(AnimeListState.Loading)

            interactor.getFilters()
                .flatMapLatest { activeFilters ->
                    mutableState.update {
                        it.copy(state = AnimeListState.Loading, filters = activeFilters)
                    }
                    flow {
                        emit(interactor.getAnimeTopList(activeFilters))
                    }
                }
                .catch { exception ->
                    val errorMessage = exception.localizedMessage ?: ""
                    updateState(AnimeListState.Error(errorMessage))
                }
                .collectLatest { animeList ->
                    if (animeList.isEmpty()) {
                        updateState(AnimeListState.Empty)
                    }
                    else {
                        updateState(AnimeListState.Success(animeList.toUiModels()))
                    }
                }
        }
    }

    fun updateFilters(filters: AnimeFilterSettings) {
        viewModelScope.launch {
            interactor.saveFilters(filters)
            mutableState.update { it.copy(filters = filters) }
        }
    }

    fun onRetryClick() {
        loadAnimeList()
    }

    fun updateState(state: AnimeListState) = mutableState.update { it.copy(state = state) }
}