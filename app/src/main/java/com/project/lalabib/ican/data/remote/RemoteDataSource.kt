package com.project.lalabib.ican.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.lalabib.ican.BuildConfig.limit
import com.project.lalabib.ican.data.local.entity.FishEntity
import com.project.lalabib.ican.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getFish(): LiveData<ApiResponse<List<FishEntity>>> {
        val resultFish = MutableLiveData<ApiResponse<List<FishEntity>>>()
        ApiClient.instance.getFish(limit).enqueue(object : Callback<List<FishEntity>> {
            override fun onResponse(call: Call<List<FishEntity>>, response: Response<List<FishEntity>>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultFish.value = ApiResponse.success(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FishEntity>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return resultFish
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }

        private const val TAG = "RemoteData"
    }
}