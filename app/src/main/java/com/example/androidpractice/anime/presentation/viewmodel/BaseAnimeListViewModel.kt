package com.example.androidpractice.anime.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime.presentation.mapper.toUiModels
import com.example.androidpractice.anime.presentation.model.AnimeFavouriteViewState
import com.example.androidpractice.anime.presentation.model.AnimeListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseAnimeListViewModel(
    private val interactor: AnimeInteractor
) : ViewModel() {
    private val mutableState = MutableStateFlow(AnimeFavouriteViewState())
    val viewState = mutableState.asStateFlow()

    private val retryTrigger = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    init {
        loadList()
    }

    abstract fun getSourceFlow(filters: AnimeFilterSettings): Flow<List<Anime>>

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadList() {
        viewModelScope.launch {
            updateState(AnimeListState.Loading)

            combine(
                interactor.getFilters(),
                retryTrigger.onStart { emit(Unit) }
            ) { filters, _ -> filters }
                .flatMapLatest { activeFilters ->
                    mutableState.update {
                        it.copy(state = AnimeListState.Loading, filters = activeFilters)
                    }
                    getSourceFlow(activeFilters)
                        .catch { exception ->
                            val errorMessage = exception.localizedMessage ?: "Неизвестная ошибка БД"
                            updateState(AnimeListState.Error(errorMessage))
                        }
                }

                .collectLatest { domainList ->
                    if (domainList.isEmpty())
                        updateState(AnimeListState.Empty)
                    else
                        updateState(AnimeListState.Success(domainList.toUiModels()))
                }
        }
    }

    fun updateFilters(filters: AnimeFilterSettings) {
        viewModelScope.launch {
            interactor.saveFilters(filters)
            mutableState.update { it.copy(filters = filters) }
        }
    }

    fun onRetryClick() = retryTrigger.tryEmit(Unit)

    fun updateState(state: AnimeListState) = mutableState.update { it.copy(state = state) }
}
