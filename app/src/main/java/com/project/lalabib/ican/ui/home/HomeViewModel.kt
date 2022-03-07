package com.project.lalabib.ican.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.lalabib.ican.data.FishRepository
import com.project.lalabib.ican.data.local.entity.FishEntity

class HomeViewModel(private val fishRepository: FishRepository) : ViewModel() {

    fun getFishs(): LiveData<List<FishEntity>> = fishRepository.getFish(limit)

    companion object {
        private const val limit = "8"
    }
}