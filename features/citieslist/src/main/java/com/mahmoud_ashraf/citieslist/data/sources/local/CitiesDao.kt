package com.mahmoud_ashraf.citieslist.data.sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahmoud_ashraf.core.entity.CityEntity
import io.reactivex.rxjava3.core.Single
import java.lang.IllegalArgumentException

@Dao
interface CitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCities(cities : List<CityEntity>)

    @Query("Select * From cities Where name = :cityName")
    fun loadCities(cityName: String): Single<List<CityEntity>>

    @Query("Select * From cities")
    fun loadCities(): Single<List<CityEntity>>

    @Query("DELETE FROM cities")
    fun deleteCities() : Int
}

fun buildCityDao()= citiesDatabase.getCitiesDao()
