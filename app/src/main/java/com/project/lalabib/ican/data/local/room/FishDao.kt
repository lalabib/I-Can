package com.project.lalabib.ican.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.lalabib.ican.data.local.entity.FishEntity

@Dao
interface FishDao {

    @Query("SELECT * FROM fish_entities")
    fun getFish(): LiveData<List<FishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFish(fish: List<FishEntity>)
}