package com.geeks.hw_6_2.data.repository

import androidx.lifecycle.LiveData
import com.geeks.hw_6_2.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import retrofit2.HttpException

//abstract class BaseRepository {
//
//    protected fun <T> doRequest(request: suspend () -> T): LiveData<Resource<T>> = liveData(
//        Dispatchers.IO
//    ) {
//        emit(Resource.Loading())
//        try {
//            val response = request()
//            if (response != null) {
//                emit(Resource.Success(response))
//            }
//        } catch (e: IOException) {
//            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
//        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
//        } catch (e: Exception) {
//            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
//        }
//    }
//}

