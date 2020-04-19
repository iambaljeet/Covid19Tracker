package com.app.covid19tracker.di

import com.app.covid19tracker.adapter.CountryUpdatesAdapter
import com.app.covid19tracker.adapter.DailyUpdatesAdapter
import com.app.covid19tracker.adapter.TotalUpdatesAdapter
import com.app.covid19tracker.db.AllPostsDatabase
import com.app.covid19tracker.db.provideAllDataDao
import com.app.covid19tracker.networking.provideApiProvider
import com.app.covid19tracker.networking.provideApiService
import com.app.covid19tracker.networking.provideHttpClient
import com.app.covid19tracker.networking.provideLoggingInterceptor
import com.app.covid19tracker.repository.DataRepository
import com.app.covid19tracker.ui.daily.DailyDataFragment
import com.app.covid19tracker.ui.daily.DailyDataViewModel
import com.app.covid19tracker.ui.home.HomeFragment
import com.app.covid19tracker.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkingModules = module {
    single {
        provideLoggingInterceptor()
    }
    single {
        provideHttpClient(get())
    }
    single {
        provideApiProvider(get())
    }
    single {
        provideApiService(get())
    }
}

@ExperimentalCoroutinesApi
val dataRepositoryModule = module {
    single {
        DataRepository(get(), get())
    }
}

@ExperimentalCoroutinesApi
val homeViewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
}

val homeFragmentModule = module {
    factory {
        HomeFragment()
    }
}

@ExperimentalCoroutinesApi
val dailyDataViewModelModule = module {
    viewModel {
        DailyDataViewModel(get())
    }
}

val countryWiseDataFragmentModule = module {
    factory {
        DailyDataFragment()
    }
}

val homeAdaptersModule = module {
    factory {
        CountryUpdatesAdapter()
    }
    factory {
        TotalUpdatesAdapter()
    }
}

val dailyDataAdaptersModule = module {
    factory {
        DailyUpdatesAdapter()
    }
}

val localDatabaseModules = module {
    factory {
        AllPostsDatabase.getDatabase(androidApplication())
    }
    single {
        provideAllDataDao(get())
    }
}