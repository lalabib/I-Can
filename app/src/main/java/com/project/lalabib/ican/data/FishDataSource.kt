package com.project.lalabib.ican.data

import androidx.lifecycle.LiveData
import com.project.lalabib.ican.data.local.FishEntity

interface FishDataSource {

    fun getFish(limit: String): LiveData<List<FishEntity>>
}