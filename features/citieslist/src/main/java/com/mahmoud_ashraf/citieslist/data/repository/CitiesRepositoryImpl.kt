package com.mahmoud_ashraf.citieslist.data.repository

import com.mahmoud_ashraf.citieslist.data.mapper.mapToCitiesResponseItem
import com.mahmoud_ashraf.citieslist.data.mapper.mapToCityEntity
import com.mahmoud_ashraf.citieslist.data.sources.local.CitiesLocalDataSourceImpl
import com.mahmoud_ashraf.citieslist.data.sources.remote.CitiesRemoteDataSourceImpl
import com.mahmoud_ashraf.citieslist.domain.repository.CitiesRepository
import com.mahmoud_ashraf.citieslist.domain.sources.local.CitiesLocalDataSource
import com.mahmoud_ashraf.citieslist.domain.sources.remote.CitiesRemoteDataSource
import com.mahmoud_ashraf.core.entity.CitiesResponse
import com.mahmoud_ashraf.core.entity.CityEntity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

class CitiesRepositoryImpl(
    private val remoteDataSource: CitiesRemoteDataSource = CitiesRemoteDataSourceImpl(),
    private val localDataSource: CitiesLocalDataSource = CitiesLocalDataSourceImpl(),

    ) : CitiesRepository {
    override fun getCities(
        page: Int,
        cityName: String?
    ): Single<Pair<List<CitiesResponse.Item>,/*isLastPage */ Boolean>> {
        return Single.just(page)
            .flatMap {pageNumber->
                if (pageNumber == 1)
                    localDataSource.deleteCities()
                else
                    Single.just(pageNumber)
            }.flatMap { remoteDataSource.getCities(page, cityName) }
            .doOnSuccess {
                localDataSource.saveCities(it.data.items.mapToCityEntity())
            }.flatMap {
                localDataSource.loadCities().zipWith(
                    Single.just(it.data.pagination.lastPage == page),
                    BiFunction { citiesList: List<CityEntity>, allPagesIsLoaded: Boolean ->
                        Pair(citiesList, allPagesIsLoaded)

                    })

            }
            .map {
                Pair(it.first.mapToCitiesResponseItem(), it.second)
            }
    }


}


