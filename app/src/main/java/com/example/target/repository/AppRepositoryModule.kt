package com.example.target.repository

import com.example.target.data.RetrofitService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppRepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        retrofitService: RetrofitService
    ): AppRepository = AppRepositoryImpl(retrofitService)
}