package com.mahmoud_ashraf.citieslist.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mahmoud_ashraf.citieslist.domain.usecases.GetCitiesListUseCase
import com.mahmoud_ashraf.core.entity.CitiesResponse
import com.nhaarman.mockito_kotlin.any
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CitiesListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `getCities() with page,cityName then fire EmptyState when no results`() {
        // arrange
        val data = listOf<CitiesResponse.Item>()
        val getCitiesListUseCase = Mockito.mock(GetCitiesListUseCase::class.java)
        Mockito.`when`(getCitiesListUseCase.fetch(any<Int>(), any<String>())).thenReturn(
            Single.just(Pair(data, false))
        )
        val viewModel = CitiesListViewModel(
            getCitiesListUseCase = getCitiesListUseCase,
            backgroundScheduler = Schedulers.trampoline()
        )
        // act
        viewModel.getCities(page = 1, cityName = "asd")
        //assert
        Assert.assertEquals(CitiesListScreenState.EmptyState, viewModel.screenState.value)
    }

    @Test
    fun `getCities() with page,cityName then fire Success With results`() {
        // arrange
        val data =
            listOf(CitiesResponse.Item(CitiesResponse.Country("egypt"), 1, 2.2, 2.4, "asyut"))
        val getCitiesListUseCase = Mockito.mock(GetCitiesListUseCase::class.java)
        Mockito.`when`(getCitiesListUseCase.fetch(any<Int>(), any<String>())).thenReturn(
            Single.just(Pair(data, false))
        )
        val viewModel = CitiesListViewModel(
            getCitiesListUseCase = getCitiesListUseCase,
            backgroundScheduler = Schedulers.trampoline()
        )
        // act
        viewModel.getCities(page = 1, cityName = "asd")
        //assert
        Assert.assertEquals(CitiesListScreenState.Success(data), viewModel.screenState.value)
    }


}