package com.example.androidpractice.anime_list.di

import com.example.androidpractice.MockData
import com.example.androidpractice.anime_list.data.api.AnimeApi
import com.example.androidpractice.anime_list.data.mapper.AnimeDtoToEntityMapper
import com.example.androidpractice.anime_list.data.repository.AnimeRepositoryImpl
import com.example.androidpractice.anime_list.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime_list.domain.repository.AnimeRepository
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeDetailsViewModel
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val animeFeatureModule = module {
    single { get<Retrofit>().create(AnimeApi::class.java)}

    single<AnimeRepository> { AnimeRepositoryImpl(get(), get()) }

    single { AnimeInteractor(get()) }
    factory { AnimeDtoToEntityMapper() }

    viewModel { AnimeListViewModel(get(), get()) }
    viewModel { AnimeDetailsViewModel(get(), get()) }
}
