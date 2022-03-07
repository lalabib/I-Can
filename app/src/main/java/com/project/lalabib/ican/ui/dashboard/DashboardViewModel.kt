package com.project.lalabib.ican.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.lalabib.ican.data.FishRepository
import com.project.lalabib.ican.data.local.entity.FishEntity

class DashboardViewModel(private val fishRepository: FishRepository) : ViewModel() {

    fun getFishDashboard(): LiveData<List<FishEntity>> = fishRepository.getFish(limit)

    companion object {
        private const val limit = "20"
    }
}