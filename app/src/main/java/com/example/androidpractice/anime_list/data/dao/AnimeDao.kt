package com.example.androidpractice.anime_list.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidpractice.anime_list.data.entity.AnimeDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM favourite_anime")
    fun getAllFavourites(): Flow<List<AnimeDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(anime: AnimeDbEntity)

    @Query("DELETE FROM favourite_anime WHERE id = :id")
    suspend fun deleteFavourite(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM favourite_anime WHERE id = :id)")
    fun isFavourite(id: Int): Flow<Boolean>
}