package com.project.lalabib.ican.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.lalabib.ican.data.FishRepository
import com.project.lalabib.ican.data.local.entity.FishEntity
import com.project.lalabib.ican.utils.Resource

class HomeViewModel(private val fishRepository: FishRepository) : ViewModel() {

    fun getFish(): LiveData<Resource<List<FishEntity>>> = fishRepository.getFish()
}