package com.mahmoud_ashraf.citieslist.domain.sources.remote

import com.mahmoud_ashraf.core.entity.CitiesResponse
import io.reactivex.rxjava3.core.Single

interface CitiesRemoteDataSource {
    fun getCities(page: Int,cityName : String?): Single<CitiesResponse>
}