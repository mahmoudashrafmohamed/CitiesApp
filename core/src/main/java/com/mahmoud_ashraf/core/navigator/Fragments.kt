package com.mahmoud_ashraf.core.navigator


object Fragments {

    object SplashFragment : AddressableFragment {
        override val className: String = "com.mahmoud_ashraf.splash.SplashFragment"
    }
    object CitiesListFragment : AddressableFragment {
        override val className: String = "com.mahmoud_ashraf.citieslist.presentation.fragment.CitiesListFragment"
    }
    object MapFragment : AddressableFragment {
        override val className: String = "com.mahmoud_ashraf.map.presentation.fragment.MapFragment"
    }


}