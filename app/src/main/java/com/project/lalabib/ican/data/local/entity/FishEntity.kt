package com.project.lalabib.ican.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fish_entities")
data class FishEntity (

    val uuid: String?,
    @PrimaryKey
    val komoditas: String,
    val area_provinsi: String?,
    val size: String?,
    val price: String?
)