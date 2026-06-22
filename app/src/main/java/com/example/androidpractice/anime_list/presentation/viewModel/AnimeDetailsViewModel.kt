package com.example.androidpractice.anime_list.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.androidpractice.anime_list.presentation.model.AnimeDetailsViewState
import com.example.androidpractice.anime_list.presentation.model.AnimeUiModel
import com.example.androidpractice.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.Route

class AnimeDetailsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val anime: AnimeUiModel
) : ViewModel() {
    private val mutableState = MutableStateFlow(AnimeDetailsViewState(anime))
    val viewState = mutableState.asStateFlow()

    fun onFavouriteChanged(isFavourite: Boolean) = mutableState.update { it.copy(isFavourite = !isFavourite) }

    fun onBack() {
        topLevelBackStack.removeLast()
    }
}