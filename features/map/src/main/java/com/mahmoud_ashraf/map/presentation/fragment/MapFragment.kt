package com.mahmoud_ashraf.map.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mahmoud_ashraf.core.CITIES_LIST
import com.mahmoud_ashraf.core.entity.CitiesResponse
import com.mahmoud_ashraf.map.R
import com.mahmoud_ashraf.map.databinding.FragmentMapBinding

class MapFragment : Fragment() , OnMapReadyCallback {


    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(mMap: GoogleMap) {
        val cities = arguments?.getParcelableArrayList<CitiesResponse.Item>(CITIES_LIST)
        cities?.filter {
            it.lat!=null && it.lng!=null
        }?.forEach {
            mMap.addMarker(
                MarkerOptions()
                .position(LatLng(it.lat?:0.0, it.lng?:0.0))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_map_icon))
            )
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.lat?:0.0, it.lng?:0.0), 12.5F))
        }
    }

}
