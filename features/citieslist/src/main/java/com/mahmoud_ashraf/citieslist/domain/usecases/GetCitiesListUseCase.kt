package com.mahmoud_ashraf.citieslist.domain.usecases

import com.mahmoud_ashraf.citieslist.data.repository.CitiesRepositoryImpl
import com.mahmoud_ashraf.citieslist.domain.repository.CitiesRepository

class GetCitiesListUseCase(private val citiesRepository: CitiesRepository = CitiesRepositoryImpl()) {
     fun fetch(page : Int,cityName : String?) =
        citiesRepository.getCities(page = page,cityName = cityName)
}