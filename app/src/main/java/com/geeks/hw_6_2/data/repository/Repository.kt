package com.geeks.hw_6_2.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geeks.hw_6_2.data.api_service.ApiService
import com.geeks.hw_6_2.data.models.BaseResponse
import com.geeks.hw_6_2.data.models.Character
import com.geeks.hw_6_2.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository  @Inject constructor(
    private val api: ApiService
) {

    fun getCharacters(): LiveData<Resource<List<Character>>> {
        val data = MutableLiveData<Resource<List<Character>>>()

        data.postValue(Resource.Loading())
        api.getCharacters().enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, responce: Response<BaseResponse>) {
                if (responce.isSuccessful && responce.body() != null) {
                    responce.body()?.let {
                        data.postValue(Resource.Success(it.results))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                data.postValue(Resource.Error(t.localizedMessage ?: "Unknown error"))
            }
        })
        return data
    }
    fun getCharacterDetails(id: Int): LiveData<Resource<Character>> {
        val data = MutableLiveData<Resource<Character>>()
        data.postValue(Resource.Loading())
        api.getCharacterDetails(id).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                Log.e("ololo", "onResponse: ${response.body()}", )
                response.body().let {
                    data.postValue(Resource.Success(response.body()!!))
                }
            }

            override fun onFailure(p0: Call<Character>, p1: Throwable) {
                Log.e("ololo", "onFailure: $p1", )
            }
        })
        return data
    }

}