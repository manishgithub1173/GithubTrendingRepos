package com.example.target.repository

import com.example.target.data.User
import io.reactivex.Observable
import retrofit2.Response

interface AppRepository {
    fun getTrendingUsers(language: String, since: String): Observable<Response<List<User>>>
}