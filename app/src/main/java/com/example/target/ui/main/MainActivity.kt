package com.example.target.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.target.R
import com.example.target.common.addFragment
import com.example.target.common.replaceFragmentWithBackStack
import com.example.target.data.User
import com.example.target.ui.trending.GithubTrendingFragment
import com.example.target.ui.userdetails.UserDetailsFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()
        if (savedInstanceState == null) {
            addWeatherFragment()
        }
    }

    private fun setUpViewModel() {
        val viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.userLiveData.observe(this, Observer {
            showUserDetailFragment(it!!)
        })
    }

    private fun addWeatherFragment() {
        addFragment(
            supportFragmentManager,
            GithubTrendingFragment.newInstance(),
            R.id.container, GithubTrendingFragment.TAG
        )
    }

    private fun showUserDetailFragment(user: User) {
        replaceFragmentWithBackStack(
            supportFragmentManager,
            UserDetailsFragment.newInstance(user),
            R.id.container, UserDetailsFragment.TAG
        )
    }
}