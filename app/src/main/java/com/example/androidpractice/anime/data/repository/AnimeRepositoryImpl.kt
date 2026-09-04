package com.example.androidpractice.anime.data.repository

import android.util.Log
import com.example.androidpractice.anime.data.api.AnimeApi
import com.example.androidpractice.anime.data.dao.AnimeDao
import com.example.androidpractice.anime.data.datastore.AnimeFilterDataStore
import com.example.androidpractice.anime.data.mapper.toDomain
import com.example.androidpractice.anime.data.mapper.toDomainList
import com.example.androidpractice.anime.data.mapper.toDomainOrNull
import com.example.androidpractice.anime.data.mapper.toEntity
import com.example.androidpractice.anime.data.mapper.toFilterSetting
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime.domain.model.FilterSetting
import com.example.androidpractice.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AnimeRepositoryImpl(
    private val api: AnimeApi,
    private val animeDao: AnimeDao,
    private val dataStore: AnimeFilterDataStore
) : AnimeRepository {
    override suspend fun getAnimeById(id: Int): Anime? = withContext(Dispatchers.IO) {
        val response = api.getAnimeById(id)
        val dto = response.data ?: throw Exception()

        dto.toDomainOrNull()
    }

    override suspend fun getAnimeTopList(
        filter: AnimeFilterSettings
    ): List<Anime> = withContext(Dispatchers.IO) {
        Log.d(filter.toString(), "Фильтр")
        val response = api.getTopAnime(
            type = filter.releaseType.apiValue,
            mainFilter = filter.mainFilter.apiValue,
            rating = filter.ageRating.apiValue,
        )
        val dtoList = response.data.orEmpty()

        dtoList.toDomainList()
    }

    override fun getFavouriteAnime(filter: AnimeFilterSettings): Flow<List<Anime>> {
        return animeDao.getFavouriteAnime(
            type = filter.releaseType.apiValue,
            rating = filter.ageRating.apiValue
        ).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addFavouriteAnime(anime: Anime) = withContext(Dispatchers.IO) {
        animeDao.insertFavouriteAnime(anime.toEntity())
    }

    override suspend fun deleteFavouriteAnime(id: Int) = withContext(Dispatchers.IO) {
        animeDao.deleteFavouriteAnime(id)
    }

    override suspend fun isAnimeFavourite(id: Int): Boolean = withContext(Dispatchers.IO) {
        return@withContext animeDao.isAnimeFavourite(id)
    }

    override suspend fun saveFilters(settings: AnimeFilterSettings) {
        dataStore.saveFilters(
            mainFilterApi = settings.mainFilter.apiValue,
            releaseTypeApi = settings.releaseType.apiValue,
            ageRatingApi = settings.ageRating.apiValue
        )
    }

    override fun getFilters(): Flow<AnimeFilterSettings> {
        return combine(
            dataStore.mainFilterFlow,
            dataStore.releaseTypeFlow,
            dataStore.ageRatingFlow
        ) { categoryStr, releaseTypeStr, ageRatingStr ->
            AnimeFilterSettings(
                mainFilter = categoryStr.toFilterSetting(FilterSetting.Category.ALL),
                releaseType = releaseTypeStr.toFilterSetting(FilterSetting.ReleaseType.ALL),
                ageRating = ageRatingStr.toFilterSetting(FilterSetting.AgeRating.ALL)
            )
        }
    }
}