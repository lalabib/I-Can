package com.project.lalabib.ican.data.local

import androidx.lifecycle.LiveData
import com.project.lalabib.ican.data.local.entity.FishEntity
import com.project.lalabib.ican.data.local.room.FishDao

class LocalDataSource(private val fishDao: FishDao) {

    fun getFish(): LiveData<List<FishEntity>> = fishDao.getFish()

    fun insertFish(fish: List<FishEntity>) = fishDao.insertFish(fish)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(fishDao: FishDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(fishDao)
    }
}