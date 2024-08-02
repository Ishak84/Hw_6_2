package com.geeks.hw_6_2.data.api_service

import com.geeks.hw_6_2.data.models.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/character")
    fun getCharacters(): Call<BaseResponse>
}