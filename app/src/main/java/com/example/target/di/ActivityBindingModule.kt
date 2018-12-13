package com.example.target.di

import com.example.target.ui.main.MainActivity
import com.example.target.ui.trending.GithubTrendingFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [GithubTrendingFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}