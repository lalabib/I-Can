package com.project.lalabib.ican.data

import androidx.lifecycle.LiveData
import com.project.lalabib.ican.data.local.entity.FishEntity
import com.project.lalabib.ican.utils.Resource

interface FishDataSource {

    fun getFish(): LiveData<Resource<List<FishEntity>>>
}