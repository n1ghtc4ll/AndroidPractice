package com.example.androidpractice.anime_list.domain.interactor

import com.example.androidpractice.anime_list.domain.model.AnimeEntity
import com.example.androidpractice.anime_list.domain.model.AnimeFilter
import com.example.androidpractice.anime_list.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime_list.domain.model.AnimeRating
import com.example.androidpractice.anime_list.domain.model.AnimeType
import com.example.androidpractice.anime_list.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(
    private val repository: AnimeRepository
) {
    suspend fun getAnimeTopList(
        type: AnimeType? = null,
        rating: AnimeRating? = null,
        filter: AnimeFilter? = null,
    ) = repository.getAnimeTopList(type, rating, filter)

    suspend fun getAnimeById(id: Int) = repository.getAnimeById(id)
    fun getFavouriteAnimeList() = repository.getFavouriteAnimeList()

    suspend fun saveFavouriteAnime(anime: AnimeEntity) = repository.saveFavouriteAnime(anime)

    suspend fun deleteFavouriteAnime(id: Int) = repository.deleteFavouriteAnime(id)

    fun isFavourite(id: Int) = repository.isFavourite(id)
    fun getFilterSettings(): Flow<AnimeFilterSettings> = repository.getFilterSettings()

    suspend fun saveFilterSettings(settings: AnimeFilterSettings) = repository.saveFilterSettings(settings)
}