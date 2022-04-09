package com.mahmoud_ashraf.citieslist.data.sources.local

import com.mahmoud_ashraf.citieslist.domain.sources.local.CitiesLocalDataSource
import com.mahmoud_ashraf.core.entity.CityEntity
import io.reactivex.rxjava3.core.Single

class CitiesLocalDataSourceImpl(  private val citiesDao: CitiesDao = buildCityDao()) : CitiesLocalDataSource {
    override fun loadCities(): Single<List<CityEntity>> {
       return citiesDao.loadCities()
    }

    override fun loadCities(name: String): Single<List<CityEntity>> {
      return citiesDao.loadCities(name)
    }

    override fun saveCities(cities: List<CityEntity>) {
       return citiesDao.saveCities(cities)
    }

    override fun deleteCities():  Single<Boolean> {
        return Single.just(citiesDao.deleteCities()==1)

    }


}