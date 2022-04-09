package com.mahmoud_ashraf.citieslist.data.sources.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahmoud_ashraf.core.bases.appContext
import com.mahmoud_ashraf.core.entity.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 3,
    exportSchema = false
)
abstract class CitiesDatabase : RoomDatabase() {
    abstract fun getCitiesDao(): CitiesDao
}

private const val databaseName = "CitiesDatabase"

val citiesDatabase =
    Room.databaseBuilder(appContext, CitiesDatabase::class.java, databaseName)
        .fallbackToDestructiveMigration()
        .build()







