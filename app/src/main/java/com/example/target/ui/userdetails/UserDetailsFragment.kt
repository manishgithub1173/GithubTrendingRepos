package com.example.target.ui.userdetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.target.R
import com.example.target.data.User
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserDetailsFragment : Fragment() {

    private lateinit var user: User

    companion object {
        var TAG = UserDetailsFragment::class.java.canonicalName!!
        var ARG_USER = "user"

        fun newInstance(user: User): UserDetailsFragment {
            val args = Bundle()
            args.putParcelable(ARG_USER, user)
            val userDetailsFragment = UserDetailsFragment()
            userDetailsFragment.arguments = args
            return userDetailsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = arguments?.getParcelable(ARG_USER)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserViews(user)
    }

    private fun setUserViews(user: User) {
        Glide.with(this)
            .load(user.avatar)
            .thumbnail(0.1f)
            .into(avatar)

        username.setSubtitle(user.username)
        name.setSubtitle(user.name)
        url.setSubtitle((user.url))

        repoName.setSubtitle(user.repo.name)
        repoDesc.setSubtitle(user.repo.description)
        repoUrl.setSubtitle(user.repo.url)
    }
}