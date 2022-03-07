package com.project.lalabib.ican.service

import com.project.lalabib.ican.data.local.entity.FishEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("storages/5e1edf521073e315924ceab4/list")
    fun getFish(@Query("limit") limit: String): Call<List<FishEntity>>
}