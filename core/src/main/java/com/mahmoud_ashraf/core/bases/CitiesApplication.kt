package com.mahmoud_ashraf.core.bases

import android.app.Application
import com.mahmoud_ashraf.core.R

lateinit var appContext: Application
 var fragmentContainerId : Int = -1


open class CitiesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}
