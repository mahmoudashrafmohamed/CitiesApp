package com.mahmoud_ashraf.citieslist.domain.usecase

import com.mahmoud_ashraf.citieslist.domain.repository.CitiesRepository
import com.mahmoud_ashraf.citieslist.domain.usecases.GetCitiesListUseCase
import com.mahmoud_ashraf.core.entity.CitiesResponse
import com.nhaarman.mockito_kotlin.any
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.Mockito
import java.net.UnknownHostException


class GetCitiesListUseCaseTest {
    @Test
    fun `fetch_GetCitiesListUseCase() with cityName, page then return Single of ListOf CityItem`() {
        // arrange
        val cityName = "cairo"
        val page = 1
        val cityItem = CitiesResponse.Item(
            CitiesResponse.Country("egypt"),
            11,
            1.21,
            2.2,
            "cairo"
        )
        val repository = Mockito.mock(CitiesRepository::class.java)
        Mockito.`when`(repository.getCities(any(), any())).thenReturn(
            Single.just(Pair(listOf(cityItem), false))
        )

        val getCitiesListUseCase = GetCitiesListUseCase(repository)

        // act
        val resultObserver = getCitiesListUseCase.fetch(page, cityName).test()

        // assert
        resultObserver.assertValue(Pair(listOf(cityItem),/*isLastPage*/  false))
        resultObserver.dispose()
    }

    @Test
    fun `fetch_GetCitiesListUseCase() with cityName, page then return Single Exception`() {
        // arrange
        val cityName = "cairo"
        val page = 1
        val exception = UnknownHostException()

        val repository = Mockito.mock(CitiesRepository::class.java)
        Mockito.`when`(repository.getCities(any(), any())).thenReturn(
            Single.error(exception)
        )
        val getCitiesListUseCase = GetCitiesListUseCase(repository)

        // act
        val resultObserver = getCitiesListUseCase.fetch(page, cityName).test()

        // assert
        resultObserver.assertError(exception)
        resultObserver.dispose()
    }

}