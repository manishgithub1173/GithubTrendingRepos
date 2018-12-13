package com.example.target.data

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("developers/")
    fun getTrendingUsers(
        @Query("language") city: String,
        @Query("since") key: String
    ): Observable<Response<List<User>>>
}