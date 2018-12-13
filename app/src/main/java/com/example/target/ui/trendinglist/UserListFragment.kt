package com.example.target.ui.trendinglist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.target.R
import com.example.target.data.User
import com.example.target.ui.main.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment(), UserListAdapter.UserInterface {
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var users: ArrayList<User>
    private lateinit var userViewModel: UserViewModel

    companion object {
        var TAG = UserListFragment::class.java.canonicalName!!
        var ARG_USERS = "users"

        fun newInstance(users: ArrayList<User>): UserListFragment {
            val args = Bundle()
            args.putParcelableArrayList(ARG_USERS, users)
            val userListFragment = UserListFragment()
            userListFragment.arguments = args
            return userListFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        users = arguments?.getParcelableArrayList(ARG_USERS)!!
        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayoutManager()
        setupAdapter()
    }

    private fun setupViewModel() {
        userViewModel = ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
    }

    private fun setupAdapter() {
        userListAdapter = context?.let { UserListAdapter(users) }!!
        list.adapter = userListAdapter
        userListAdapter.setListener(this)
    }

    private fun setupLayoutManager() {
        val layoutManager = LinearLayoutManager(context)
        list.layoutManager = layoutManager
        list.addItemDecoration(DividerItemDecoration(
            context,
                DividerItemDecoration.VERTICAL))
    }

    override fun onUserClick(user: User) {
        userViewModel.userLiveData.value = user
    }
}