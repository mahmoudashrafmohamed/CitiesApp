package com.mahmoud_ashraf

import com.mahmoud_ashraf.citiesapp.R
import com.mahmoud_ashraf.core.bases.CitiesApplication
import com.mahmoud_ashraf.core.bases.fragmentContainerId


class App : CitiesApplication() {
    override fun onCreate() {
        super.onCreate()
        fragmentContainerId = R.id.fl_app_screens
    }
}