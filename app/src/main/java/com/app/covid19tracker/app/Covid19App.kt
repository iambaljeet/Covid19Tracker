package com.app.covid19tracker.app

import android.app.Application
import com.app.covid19tracker.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Covid19App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Covid19App)
            modules(
                listOf(
                    networkingModules, dataRepositoryModule,
                    homeViewModelModule, homeFragmentModule,
                    countryWiseDataFragmentModule, homeAdaptersModule,
                    localDatabaseModules, dailyDataViewModelModule,
                    dailyDataAdaptersModule
                )
            )
        }
    }
}