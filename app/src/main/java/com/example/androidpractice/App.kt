package com.example.androidpractice

import android.app.Application
import com.example.androidpractice.anime.di.animeFeatureModule
import com.example.androidpractice.core.di.dataModule
import com.example.androidpractice.core.di.mainModule
import com.example.androidpractice.core.di.networkModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                mainModule,
                networkModule,
                dataModule,
                animeFeatureModule
            )
        }
    }
}