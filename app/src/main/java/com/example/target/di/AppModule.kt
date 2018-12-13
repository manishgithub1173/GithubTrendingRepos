package com.example.target.di

import android.content.Context
import com.example.target.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module(includes = [
        NetworkModule::class])
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: MainApplication): Context {
        return application.applicationContext
    }
}