package com.mahmoud_ashraf.core.exceptions

sealed class CitiesAppException : Throwable(){
    object NoConnection : CitiesAppException()
    object UnAuthorized : CitiesAppException()
    object ServerDown : CitiesAppException()
}

