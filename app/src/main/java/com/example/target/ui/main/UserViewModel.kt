package com.example.target.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.target.data.User

class UserViewModel : ViewModel() {
    var userLiveData = MutableLiveData<User>()
}