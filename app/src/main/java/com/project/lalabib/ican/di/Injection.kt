package com.project.lalabib.ican.di

import com.project.lalabib.ican.data.FishRepository
import com.project.lalabib.ican.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(): FishRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return FishRepository.getInstance(remoteDataSource)
    }
}