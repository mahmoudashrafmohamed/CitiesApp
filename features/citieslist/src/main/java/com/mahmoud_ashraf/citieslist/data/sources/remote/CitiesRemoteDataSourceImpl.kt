package com.mahmoud_ashraf.citieslist.data.sources.remote

import com.mahmoud_ashraf.citieslist.domain.sources.remote.CitiesRemoteDataSource
import com.mahmoud_ashraf.core.data.remote.buildApi
import com.mahmoud_ashraf.core.entity.CitiesResponse
import io.reactivex.rxjava3.core.Single

class CitiesRemoteDataSourceImpl(private val citiesApi: CitiesApi = buildApi()) : CitiesRemoteDataSource {
    override fun getCities(page: Int, cityName : String?): Single<CitiesResponse> {
        return citiesApi.getCities(page=page, cityName = cityName)
    }

}