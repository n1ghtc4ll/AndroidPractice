package com.example.androidpractice.anime.di

import com.example.androidpractice.anime.data.api.AnimeApi
import com.example.androidpractice.anime.data.dao.AnimeDao
import com.example.androidpractice.anime.data.mapper.AnimeDtoToDomainMapper
import com.example.androidpractice.anime.data.repository.AnimeRepositoryImpl
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.repository.AnimeRepository
import com.example.androidpractice.anime.presentation.viewModel.AnimeDetailsViewModel
import com.example.androidpractice.anime.presentation.viewModel.AnimeListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val animeFeatureModule = module {
    single { get<Retrofit>().create(AnimeApi::class.java)}

    single<AnimeRepository> { AnimeRepositoryImpl(get(), get(), get()) }

    single { AnimeInteractor(get()) }
    factory { AnimeDtoToDomainMapper() }

    viewModel { AnimeListViewModel(get(), get()) }
    viewModel { AnimeDetailsViewModel(get(), get()) }
}
