package com.example.target.repository

import com.example.target.data.RetrofitService
import com.example.target.data.User
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : AppRepository {
    override fun getTrendingUsers(language: String, since: String): Observable<Response<List<User>>> {
        return retrofitService.getTrendingUsers(language, since)
    }
}