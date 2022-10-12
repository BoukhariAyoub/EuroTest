package com.example.eurotest.data.remote

import com.example.eurotest.data.remote.model.MainResponse
import retrofit2.http.GET

interface RetrofitService {

    @GET("json-storage/bin/edfefba")
    suspend fun get(): MainResponse
}











