package com.project.lalabib.ican.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.lalabib.ican.data.local.entity.FishEntity

@Database(entities = [FishEntity::class], version = 1, exportSchema = false)
abstract class FishDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: FishDatabase? = null

        fun getInstance(context: Context): FishDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FishDatabase::class.java,
                    "Fish.db"
                ).build()
            }
    }

    abstract fun fishDao(): FishDao
}