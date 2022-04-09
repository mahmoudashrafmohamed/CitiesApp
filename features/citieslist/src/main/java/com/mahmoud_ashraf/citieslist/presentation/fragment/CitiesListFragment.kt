package com.mahmoud_ashraf.citieslist.presentation.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud_ashraf.citieslist.R
import com.mahmoud_ashraf.citieslist.databinding.FragmentCitiesListBinding
import com.mahmoud_ashraf.citieslist.presentation.adapter.CitiesListAdapter
import com.mahmoud_ashraf.citieslist.presentation.viewmodel.CitiesListScreenState
import com.mahmoud_ashraf.citieslist.presentation.viewmodel.CitiesListViewModel
import com.mahmoud_ashraf.core.CITIES_LIST
import com.mahmoud_ashraf.core.androidExtensions.addFragment
import com.mahmoud_ashraf.core.androidExtensions.observe
import com.mahmoud_ashraf.core.entity.CitiesResponse
import com.mahmoud_ashraf.core.exceptions.CitiesAppException
import com.mahmoud_ashraf.core.navigator.Fragments
import com.mahmoud_ashraf.core.pagination.EndlessRecyclerViewScrollListener


class CitiesListFragment : Fragment() {

    private val viewModelProvider: (ViewModelStoreOwner) -> CitiesListViewModel = {
        ViewModelProvider(it)[CitiesListViewModel::class.java]
    }
    private val viewModel by lazy { viewModelProvider(this) }
    private val citiesListAdapter by lazy { CitiesListAdapter() }
    private var _binding: FragmentCitiesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe(viewModel.screenState, ::onScreenStateChange)
        viewModel.getCities()
    }

    private fun initView() {
        binding.rvCities.adapter = citiesListAdapter
        binding.rvCities.addOnScrollListener(object : EndlessRecyclerViewScrollListener(
            binding.rvCities.layoutManager as LinearLayoutManager
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.loadMoreCities()
            }
        })
        binding.btnSearch.setOnClickListener {
            viewModel.filterByCityName(binding.etCityName.text.toString())
        }

        binding.btnShowOnMap.setOnClickListener {
            navigateToMapFragment()
        }
    }

    private fun navigateToMapFragment() {
        addFragment(Fragments.MapFragment, bundle = Bundle().apply {
            putParcelableArrayList(
                CITIES_LIST,
                citiesListAdapter.data as ArrayList<out Parcelable>?
            )
        })
    }

    private fun onScreenStateChange(state: CitiesListScreenState) {
        when (state) {
            is CitiesListScreenState.Loading -> showLoading()
            is CitiesListScreenState.Success -> handleSuccessState(state.data)
            is CitiesListScreenState.Error ->  handleErrorState(state.error)
            is CitiesListScreenState.EmptyState -> showEmptyView()
        }
    }

    private fun showEmptyView() {
        hideLoading()
        binding.rvCities.isVisible = false
        binding.emptyView.isVisible = true
    }

    private fun handleErrorState(error:CitiesAppException) {
        hideLoading()
        error.handle()
    }

    private fun handleSuccessState(data: List<CitiesResponse.Item>) {
        hideLoading()
        binding.rvCities.isVisible = true
        citiesListAdapter.submitList(data)
    }

    private fun CitiesAppException.handle() {
        when (this) {
            is CitiesAppException.NoConnection -> Toast.makeText(context, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(context, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}