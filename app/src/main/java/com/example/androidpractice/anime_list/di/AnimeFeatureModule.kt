package com.example.androidpractice.anime_list.di

import androidx.room.Room
import com.example.androidpractice.MockData
import com.example.androidpractice.anime_list.data.api.AnimeApi
import com.example.androidpractice.anime_list.data.db.AppDatabase
import com.example.androidpractice.anime_list.data.mapper.AnimeDtoToEntityMapper
import com.example.androidpractice.anime_list.data.mapper.AnimeFavouriteMapper
import com.example.androidpractice.anime_list.data.repository.AnimeRepositoryImpl
import com.example.androidpractice.anime_list.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime_list.domain.repository.AnimeRepository
import com.example.androidpractice.anime_list.presentation.cache.FilterBadgeCache
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeDetailsViewModel
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeFavouritesViewModel
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val animeFeatureModule = module {
    single { get<Retrofit>().create(AnimeApi::class.java)}

    single<AnimeRepository> { AnimeRepositoryImpl(get(), get(),get(), get()) }

    single { AnimeInteractor(get()) }

    factory { AnimeDtoToEntityMapper() }
    factory { AnimeFavouriteMapper() }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "anime_database"
        ).build()
    }
    single { get<AppDatabase>().animeDao() }

    single { FilterBadgeCache() }

    viewModel { AnimeListViewModel(get(), get(), get()) }
    viewModel { AnimeDetailsViewModel(get(), get(), get()) }
    viewModel { AnimeFavouritesViewModel(get()) }
}
