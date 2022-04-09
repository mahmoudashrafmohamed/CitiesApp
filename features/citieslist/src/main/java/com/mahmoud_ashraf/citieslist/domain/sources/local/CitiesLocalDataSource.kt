package com.mahmoud_ashraf.citieslist.domain.sources.local

import com.mahmoud_ashraf.core.entity.CityEntity
import io.reactivex.rxjava3.core.Single

interface CitiesLocalDataSource {
    fun loadCities(): Single<List<CityEntity>>
    fun loadCities(name : String): Single<List<CityEntity>>
    fun saveCities(cities : List<CityEntity>)
    fun deleteCities():Single<Boolean>
}