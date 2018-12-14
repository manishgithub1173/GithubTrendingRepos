package com.example.target.ui.trending

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import com.example.target.R
import com.example.target.common.Constants
import com.example.target.common.EspressoIdlingResource
import com.example.target.data.User
import com.example.target.ui.trendinglist.UserListFragment
import kotlinx.android.synthetic.main.fragment_github_trending.*
import java.io.IOException

class GithubTrendingFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var githubTrendingFragmentViewModel: GithubTrendingFragmentViewModel

    companion object {
        var TAG = GithubTrendingFragment::class.java.canonicalName!!

        fun newInstance(): GithubTrendingFragment {
            return GithubTrendingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        initObserver()
        getTrendingUsers()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_github_trending, container, false)
    }

    private fun getTrendingUsers() {
        EspressoIdlingResource.increment()
        githubTrendingFragmentViewModel.getTrendingUsers(Constants.LANGUAGE, Constants.SINCE)
    }

    private fun setupViewModel() {
        githubTrendingFragmentViewModel = ViewModelProviders.of(
            this,
            viewModelFactory).get(GithubTrendingFragmentViewModel::class.java
        )
    }

    private fun initObserver() {
        val loadingCallbackObserver =
            Observer<Boolean> { loading ->
                showProgressBar(loading!!)
            }

        val successCallbackObserver =
            Observer<List<User>> { users ->
                users?.let { onSuccess(it) }
            }

        val errorCallbackObserver =
            Observer<Throwable> { throwable ->
                onError(throwable)
            }

        githubTrendingFragmentViewModel.getLoadingValue().observe(this, loadingCallbackObserver)
        githubTrendingFragmentViewModel.getUsers().observe(this, successCallbackObserver)
        githubTrendingFragmentViewModel.getErrorValue().observe(this, errorCallbackObserver)
    }

    private fun showProgressBar(loading: Boolean) {
        if (loading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun onSuccess(users: List<User>) {
        showUsers(users)
        EspressoIdlingResource.decrement()
    }

    private fun onError(throwable: Throwable?) {
        var errorMessage = getString(R.string.something_went_wrong)
        when (throwable) {
            is ConnectException -> {
                errorMessage = getString(R.string.no_network)
            }
            is UnknownHostException -> {
                errorMessage = getString(R.string.no_network)
            }
            is HttpException -> {
                errorMessage = throwable.localizedMessage
            }
            is IOException -> {
                errorMessage = getString(R.string.no_network)
            }
        }
        EspressoIdlingResource.decrement()
    }

    private fun showUsers(users: List<User>) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.trendingRepoContainer,
            UserListFragment.newInstance(users as ArrayList<User>),
            UserListFragment.TAG
        )
        fragmentTransaction.commit()
    }
}