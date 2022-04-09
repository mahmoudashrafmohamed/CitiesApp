package com.mahmoud_ashraf.citiesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoud_ashraf.core.androidExtensions.replaceFragment
import com.mahmoud_ashraf.core.navigator.Fragments

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(Fragments.SplashFragment)
    }
}