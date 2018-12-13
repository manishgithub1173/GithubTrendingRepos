package com.example.target.ui.main

import android.os.Bundle
import com.example.target.R
import com.example.target.common.addFragment
import com.example.target.ui.trending.GithubTrendingFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addWeatherFragment()
        }
    }

    private fun addWeatherFragment() {
        addFragment(
            supportFragmentManager,
            GithubTrendingFragment.newInstance(),
            R.id.container, GithubTrendingFragment.TAG
        )
    }
}