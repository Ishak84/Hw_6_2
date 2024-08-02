package com.geeks.hw_6_2.data.repository

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
}