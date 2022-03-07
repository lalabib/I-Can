package com.project.lalabib.ican.di

import android.content.Context
import com.project.lalabib.ican.data.FishRepository
import com.project.lalabib.ican.data.local.LocalDataSource
import com.project.lalabib.ican.data.local.room.FishDatabase
import com.project.lalabib.ican.data.remote.RemoteDataSource
import com.project.lalabib.ican.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): FishRepository {

        val database = FishDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.fishDao())
        val appExecutors = AppExecutors()

        return FishRepository.getInstance(remoteDataSource,localDataSource, appExecutors)
    }
}