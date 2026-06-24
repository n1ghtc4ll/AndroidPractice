package com.example.androidpractice.anime_list.data.repository

import com.example.androidpractice.anime_list.data.api.AnimeApi
import com.example.androidpractice.anime_list.data.dao.AnimeDao
import com.example.androidpractice.anime_list.data.datastore.AnimeFilterDataStore
import com.example.androidpractice.anime_list.data.mapper.AnimeDtoToEntityMapper
import com.example.androidpractice.anime_list.data.mapper.AnimeFavouriteMapper
import com.example.androidpractice.anime_list.data.mapper.FilterSettingsMapper
import com.example.androidpractice.anime_list.domain.model.AnimeEntity
import com.example.androidpractice.anime_list.domain.model.AnimeFilter
import com.example.androidpractice.anime_list.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime_list.domain.model.AnimeRating
import com.example.androidpractice.anime_list.domain.model.AnimeType
import com.example.androidpractice.anime_list.domain.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AnimeRepositoryImpl(
    private val api: AnimeApi,
    private val dao: AnimeDao,
    private val filterDataStore: AnimeFilterDataStore,
    private val dtoMapper: AnimeDtoToEntityMapper,
    private val favouriteMapper: AnimeFavouriteMapper,
    private val filterMapper: FilterSettingsMapper
) : AnimeRepository {
    override suspend fun getAnimeById(id: Int): AnimeEntity? = withContext(Dispatchers.IO) {
        val response = api.getAnimeById(id)
        val dto = response.data ?: throw Exception()

        dtoMapper.mapItem(dto)
    }

    override suspend fun getAnimeTopList(
        type: AnimeType?,
        rating: AnimeRating?,
        filter: AnimeFilter?,
    ): List<AnimeEntity> = withContext(Dispatchers.IO) {
        val response = api.getTopAnime(type?.apiValue, rating?.apiValue, filter?.apiValue)
        val dtoList = response.data.orEmpty()

        dtoMapper.mapList(dtoList)
    }

    override fun getFavouriteAnimeList(): Flow<List<AnimeEntity>> {
        return dao.getAllFavourites().map { dbList ->
            dbList.map { dbItem -> favouriteMapper.mapToDomain(dbItem) }
        }
    }

    override suspend fun saveFavouriteAnime(anime: AnimeEntity) {
        val dbEntity = favouriteMapper.mapToDb(anime)
        dao.insertFavourite(dbEntity)
    }

    override suspend fun deleteFavouriteAnime(id: Int) {
        dao.deleteFavourite(id)
    }

    override fun isFavourite(id: Int): Flow<Boolean> {
        return dao.isFavourite(id)
    }

    override fun getFilterSettings(): Flow<AnimeFilterSettings> {
        return filterDataStore.filterStringsFlow.map { (type, filter, rating) ->
            filterMapper.mapToDomain(type, filter, rating)
        }
    }

    override suspend fun saveFilterSettings(settings: AnimeFilterSettings) {
        filterDataStore.saveFilterStrings(
            type = settings.type.name,
            filter = settings.filter.name,
            rating = settings.rating.name
        )
    }
}
