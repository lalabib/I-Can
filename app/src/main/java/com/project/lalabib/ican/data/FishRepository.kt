package com.project.lalabib.ican.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.lalabib.ican.data.local.FishEntity
import com.project.lalabib.ican.data.remote.RemoteDataSource

class FishRepository private constructor(private val remoteDataSource: RemoteDataSource)
    :FishDataSource {

    companion object {
        @Volatile
        private var instance: FishRepository? = null

        fun getInstance(remoteData: RemoteDataSource): FishRepository =
            instance ?: synchronized(this) {
                instance ?: FishRepository(remoteData).apply { instance = this }
            }
    }

    override fun getFish(limit: String): LiveData<List<FishEntity>> {
        val fish = MutableLiveData<List<FishEntity>>()
        remoteDataSource.getFish(limit, object : RemoteDataSource.LoadFishCallback {
            override fun onAllFishReceived(fishResponse: List<FishEntity>) {
                fish.postValue(fishResponse)
            }
        })
        return fish
    }
}