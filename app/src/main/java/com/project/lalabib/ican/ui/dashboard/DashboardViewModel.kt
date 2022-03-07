package com.project.lalabib.ican.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.lalabib.ican.data.FishRepository
import com.project.lalabib.ican.data.local.entity.FishEntity
import com.project.lalabib.ican.utils.Resource

class DashboardViewModel(private val fishRepository: FishRepository) : ViewModel() {

    fun getFishDashboard(): LiveData<Resource<List<FishEntity>>> = fishRepository.getFish()
}