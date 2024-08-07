package com.geeks.hw_6_2.data.api_service

import com.geeks.hw_6_2.data.models.BaseResponse
import retrofit2.Call
import com.geeks.hw_6_2.data.models.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/character")
    fun getCharacters(): Call<BaseResponse>

    @GET("character/{id}")
    fun getCharacterDetails(@Path("id") id: Int): Call<Character>
}