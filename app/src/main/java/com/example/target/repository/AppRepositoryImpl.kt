package com.example.target.repository

import com.example.target.data.RetrofitService
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : AppRepository