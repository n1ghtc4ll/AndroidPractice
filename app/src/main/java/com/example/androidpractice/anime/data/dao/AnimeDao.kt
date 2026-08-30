package com.example.androidpractice.anime.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidpractice.anime.data.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM favourite_anime WHERE type = :type AND rating = :rating")
    fun getFavouriteAnime(
        type: String?,
        //filter: String?,
        rating: String?,
        //sfw: Boolean?
    ): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteAnime(anime: AnimeEntity)

    @Query("DELETE FROM favourite_anime WHERE mal_id = :id")
    suspend fun deleteFavouriteAnime(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_anime WHERE mal_id = :id)")
    suspend fun isAnimeFavourite(id: Int) : Boolean
}