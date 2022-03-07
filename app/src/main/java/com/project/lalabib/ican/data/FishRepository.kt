package com.project.lalabib.ican.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.lalabib.ican.data.local.LocalDataSource
import com.project.lalabib.ican.data.local.entity.FishEntity
import com.project.lalabib.ican.data.remote.ApiResponse
import com.project.lalabib.ican.data.remote.RemoteDataSource
import com.project.lalabib.ican.utils.AppExecutors
import com.project.lalabib.ican.utils.Resource

class FishRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : FishDataSource {

    override fun getFish(): LiveData<Resource<List<FishEntity>>> {
        return object : NetworkBoundResource<List<FishEntity>, List<FishEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<FishEntity>> {
                return localDataSource.getFish()
            }
            override fun shouldFetch(data: List<FishEntity>?): Boolean =
                //data == null || data.isEmpty()
                true

            public override fun createCall(): LiveData<ApiResponse<List<FishEntity>>> =
                remoteDataSource.getFish()

            public override fun saveCallResult(data: List<FishEntity>) {
                localDataSource.insertFish(data)
            }
        }.asLiveData()
    }

    companion object {
        @Volatile
        private var instance: FishRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FishRepository =
            instance ?: synchronized(this) {
                instance ?: FishRepository(remoteData, localData, appExecutors ).apply {
                    instance = this
                }
            }
    }
}