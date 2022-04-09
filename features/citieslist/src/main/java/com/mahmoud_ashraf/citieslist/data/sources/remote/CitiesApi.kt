package com.mahmoud_ashraf.citieslist.data.sources.remote

import com.mahmoud_ashraf.core.entity.CitiesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesApi {

    @GET("city")
    fun getCities(
        @Query("page") page: Int,
        @Query("include") include: String = "country",
        @Query("filter[0][name][contains]") cityName: String? = null
    ): Single<CitiesResponse>
}