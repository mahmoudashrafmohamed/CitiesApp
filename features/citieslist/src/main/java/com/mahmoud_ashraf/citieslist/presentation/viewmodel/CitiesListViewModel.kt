package com.mahmoud_ashraf.citieslist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mahmoud_ashraf.citieslist.domain.usecases.GetCitiesListUseCase
import com.mahmoud_ashraf.core.bases.BaseViewModel
import com.mahmoud_ashraf.core.entity.CitiesResponse
import com.mahmoud_ashraf.core.exceptions.CitiesAppException
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class CitiesListViewModel(
    private val getCitiesListUseCase: GetCitiesListUseCase = GetCitiesListUseCase(),
    private val backgroundScheduler: Scheduler = Schedulers.io()
) :
    BaseViewModel() {

    internal val screenState by lazy { MutableLiveData<CitiesListScreenState>() }

    private var pageNumber = 1
    private var cityName: String? = null
    private var allPagesIsLoaded = false
    private val citiesCachedList = mutableListOf<CitiesResponse.Item>()

    fun getCities() {
        if (allPagesIsLoaded) return
        screenState.postValue(CitiesListScreenState.Loading)
        getCitiesListUseCase.fetch(pageNumber, cityName)
            .observeOn(backgroundScheduler)
            .subscribeOn(backgroundScheduler)
            .subscribe({
                pageNumber++
                allPagesIsLoaded = it.second
                citiesCachedList.addAll(it.first)
                if (citiesCachedList.isEmpty())
                    screenState.postValue(CitiesListScreenState.EmptyState)
                    else
                screenState.postValue(CitiesListScreenState.Success(citiesCachedList))
            }, {
                it.printStackTrace()
                screenState.postValue(CitiesListScreenState.Error(handleError(it)))

            })
            .also(::addDisposable)
    }


    fun loadMoreCities() =
        getCities()

    fun filterByCityName(cityName: String) {
        resetCachedData(cityName)
        getCities()
    }

    private fun resetCachedData(cityName: String) {
        this.pageNumber = 1
        this.cityName = cityName
        this.allPagesIsLoaded = false
        citiesCachedList.clear()
    }
    }

sealed class CitiesListScreenState {
    object Loading : CitiesListScreenState()
    object EmptyState : CitiesListScreenState()
    data class Success(val data: List<CitiesResponse.Item>) : CitiesListScreenState()
    data class Error(val error: CitiesAppException) : CitiesListScreenState()
}