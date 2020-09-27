package com.app.covid19tracker.app

import android.app.Application
import com.app.covid19tracker.di.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Covid19App : Application() {
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Covid19App)

            // TODO Await fix for Koin and replace the explicit invocations
            //  of loadModules() and createRootScope() with a single call to modules()
            //  (https://github.com/InsertKoinIO/koin/issues/847)
            koin.loadModules(
                listOf(
                    networkingModules, dataRepositoryModule,
                    homeViewModelModule, homeFragmentModule,
                    countryWiseDataFragmentModule, homeAdaptersModule,
                    localDatabaseModules, dailyDataViewModelModule,
                    dailyDataAdaptersModule
                )
            )
            koin.createRootScope()
        }
    }
}