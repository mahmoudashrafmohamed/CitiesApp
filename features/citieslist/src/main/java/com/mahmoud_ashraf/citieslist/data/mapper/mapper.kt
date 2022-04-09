package com.mahmoud_ashraf.citieslist.data.mapper

import com.mahmoud_ashraf.core.entity.CitiesResponse
import com.mahmoud_ashraf.core.entity.CityEntity

 fun List<CitiesResponse.Item>.mapToCityEntity(): List<CityEntity> {
    return this.map {
        CityEntity(it.id, it.lat, it.lng, it.name, it.country.name)
    }
}

 fun List<CityEntity>.mapToCitiesResponseItem(): List<CitiesResponse.Item> {
    return this.map {
        CitiesResponse.Item(CitiesResponse.Country(it.country), it.id, it.lat, it.lng, it.name)
    }
}
