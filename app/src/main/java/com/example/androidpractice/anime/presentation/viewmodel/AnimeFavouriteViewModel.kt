package com.example.androidpractice.anime.presentation.viewmodel

import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import kotlinx.coroutines.flow.Flow


class AnimeFavouriteViewModel(
    private val interactor: AnimeInteractor
) : BaseAnimeListViewModel(interactor) {

    override fun getSourceFlow(filters: AnimeFilterSettings): Flow<List<Anime>> {
        return interactor.getFavouriteAnime(filters)
    }
}