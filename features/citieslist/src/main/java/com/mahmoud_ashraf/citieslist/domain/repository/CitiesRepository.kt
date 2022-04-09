package com.mahmoud_ashraf.citieslist.domain.repository

import com.mahmoud_ashraf.core.entity.CitiesResponse
import io.reactivex.rxjava3.core.Single

interface CitiesRepository {
    fun getCities(page: Int,cityName : String?): Single<Pair<List<CitiesResponse.Item>,/*isLastPage */Boolean>>
}