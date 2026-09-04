package com.example.androidpractice.anime.di

import com.example.androidpractice.anime.data.api.AnimeApi
import com.example.androidpractice.anime.data.datastore.AnimeFilterDataStore
import com.example.androidpractice.anime.data.repository.AnimeRepositoryImpl
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.repository.AnimeRepository
import com.example.androidpractice.anime.presentation.viewmodel.AnimeDetailsViewModel
import com.example.androidpractice.anime.presentation.viewmodel.AnimeFavouriteViewModel
import com.example.androidpractice.anime.presentation.viewmodel.AnimeMainListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val animeFeatureModule = module {
    single { get<Retrofit>().create(AnimeApi::class.java)}

    single { AnimeFilterDataStore(androidContext())}

    single<AnimeRepository> { AnimeRepositoryImpl(get(), get(), get()) }

    single { AnimeInteractor(get()) }

    viewModel { AnimeMainListViewModel(get()) }
    viewModel { AnimeDetailsViewModel(get(), get()) }
    viewModel { AnimeFavouriteViewModel(get()) }
}
