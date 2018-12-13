package com.example.target.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, tag: String) {
    checkNotNull(fragmentManager)
    checkNotNull(fragment)
    val transaction = fragmentManager.beginTransaction()
    transaction.add(frameId, fragment, tag)
    transaction.commitAllowingStateLoss()
}
