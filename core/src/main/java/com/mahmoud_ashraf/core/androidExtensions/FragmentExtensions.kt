package com.mahmoud_ashraf.core.androidExtensions

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mahmoud_ashraf.core.R
import com.mahmoud_ashraf.core.bases.fragmentContainerId
import com.mahmoud_ashraf.core.navigator.AddressableFragment

fun <T> Fragment.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer { it?.let(observer) })
}

fun Fragment.addFragment( addressableFragment: AddressableFragment,containerId : Int= fragmentContainerId,bundle: Bundle?=null) {
    val fragment : Fragment= fragmentOf(addressableFragment)
       fragment.arguments = bundle
    this.activity?.supportFragmentManager?.beginTransaction()
        ?.add(containerId,fragment)
        ?.addToBackStack(addressableFragment.className)
        ?.commit()

}

fun AppCompatActivity.replaceFragment( addressableFragment: AddressableFragment,containerId : Int= fragmentContainerId,bundle: Bundle?=null) {
    val fragment : Fragment= fragmentOf(addressableFragment)
       fragment.arguments = bundle
    supportFragmentManager.beginTransaction()
        .replace(containerId,fragment)
        .commit()

}

fun <T : Fragment> fragmentOf(addressableFragment: AddressableFragment): T {
    return Class.forName(addressableFragment.className).newInstance() as T
}

