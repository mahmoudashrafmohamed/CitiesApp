package com.mahmoud_ashraf.core.bases

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mahmoud_ashraf.core.data.remote.NoConnectivityException
import com.mahmoud_ashraf.core.exceptions.CitiesAppException
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun handleError(it: Throwable): CitiesAppException {
        when (it) {
            is NoConnectivityException -> {
                return CitiesAppException.NoConnection
            }
            is HttpException -> {
                return when (it.code()) {
                    401 -> {
                        CitiesAppException.UnAuthorized
                    }
                    else -> {
                        CitiesAppException.ServerDown
                    }
                }

            }
            else -> return CitiesAppException.ServerDown
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}