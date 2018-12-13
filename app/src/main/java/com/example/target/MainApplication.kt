package com.example.target

import com.example.target.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    companion object {
        var instance: MainApplication? = null
            private set
    }

    override fun applicationInjector(): AndroidInjector<MainApplication> =
        DaggerAppComponent.builder().create(this@MainApplication)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}