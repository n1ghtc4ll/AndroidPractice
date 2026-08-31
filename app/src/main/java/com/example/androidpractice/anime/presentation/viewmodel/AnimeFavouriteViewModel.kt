package com.example.androidpractice.anime.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime.presentation.mapper.toUiModels
import com.example.androidpractice.anime.presentation.model.AnimeFavouriteViewState
import com.example.androidpractice.anime.presentation.model.AnimeListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AnimeFavouriteViewModel(
    private val animeInteractor: AnimeInteractor
) : ViewModel() {
    private val mutableState = MutableStateFlow(AnimeFavouriteViewState())
    val viewState = mutableState.asStateFlow()

    init {
        loadFavouriteList()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadFavouriteList() {
        viewModelScope.launch {
            updateState(AnimeListState.Loading)

            animeInteractor.getFilters()
                .flatMapLatest { activeFilters ->
                    mutableState.update {
                        it.copy(state = AnimeListState.Loading, filters = activeFilters)
                    }
                    animeInteractor.getFavouriteAnime(activeFilters)
                }
                .catch { exception ->
                    val errorMessage = exception.localizedMessage ?: "Неизвестная ошибка БД"
                    updateState(AnimeListState.Error(errorMessage))
                }
                .collectLatest { domainList ->
                    if (domainList.isEmpty())
                        updateState(AnimeListState.Empty)
                    else
                        updateState(AnimeListState.Success(domainList.toUiModels()))
                }
        }
    }

    suspend fun updateFilters(filters: AnimeFilterSettings) {
        animeInteractor.saveFilters(filters)
        mutableState.update { it.copy(filters = filters) }
    }

    fun onRetryClick() = loadFavouriteList()

    fun updateState(state: AnimeListState) = mutableState.update { it.copy(state = state) }
}