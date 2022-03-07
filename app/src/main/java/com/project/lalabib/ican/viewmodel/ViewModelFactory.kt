package com.project.lalabib.ican.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.lalabib.ican.data.FishRepository
import com.project.lalabib.ican.di.Injection
import com.project.lalabib.ican.ui.dashboard.DashboardViewModel
import com.project.lalabib.ican.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val fishRepository: FishRepository)
    : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance
                    ?: ViewModelFactory(Injection.provideRepository()).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(fishRepository) as T
            }
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(fishRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}