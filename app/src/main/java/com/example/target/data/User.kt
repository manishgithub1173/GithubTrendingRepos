package com.example.target.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String,
    val name: String,
    val url: String,
    val avatar: String,
    val repo: Repo
) : Parcelable

@Parcelize
data class Repo(
    val name: String,
    val description: String,
    val url: String
) : Parcelable