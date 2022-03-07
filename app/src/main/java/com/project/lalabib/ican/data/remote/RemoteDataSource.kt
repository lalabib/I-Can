package com.project.lalabib.ican.data.remote

import android.util.Log
import com.project.lalabib.ican.data.local.FishEntity
import com.project.lalabib.ican.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }

        private const val TAG = "RemoteData"
    }

    fun getFish(limit: String, callback: LoadFishCallback) {
        ApiClient.instance.getFish(limit).enqueue(object : Callback<List<FishEntity>> {
            override fun onResponse(call: Call<List<FishEntity>>, response: Response<List<FishEntity>>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onAllFishReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FishEntity>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface LoadFishCallback {
        fun onAllFishReceived(fishResponse: List<FishEntity>)
    }
}