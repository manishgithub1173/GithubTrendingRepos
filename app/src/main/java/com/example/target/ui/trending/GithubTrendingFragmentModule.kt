package com.example.target.ui.trending

import android.arch.lifecycle.ViewModel
import com.example.target.di.FragmentScoped
import com.example.target.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class GithubTrendingFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeGithubTrendingFragment(): GithubTrendingFragment

    @Binds
    @IntoMap
    @ViewModelKey(GithubTrendingFragmentViewModel::class)
    abstract fun bindWeatherFragmentViewModel(
        githubTrendingFragmentViewModel: GithubTrendingFragmentViewModel
    ): ViewModel
}