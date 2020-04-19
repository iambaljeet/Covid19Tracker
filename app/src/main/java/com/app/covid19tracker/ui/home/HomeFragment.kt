package com.app.covid19tracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.MergeAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.covid19tracker.R
import com.app.covid19tracker.adapter.CountryUpdatesAdapter
import com.app.covid19tracker.adapter.TotalUpdatesAdapter
import com.app.covid19tracker.model.CountryCaseModel
import com.app.covid19tracker.model.DataResult
import com.app.covid19tracker.model.GlobalDataModel
import com.app.covid19tracker.utility.getFormattedDate
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    @ExperimentalCoroutinesApi
    private val homeViewModel by viewModel<HomeViewModel>()

    private val totalUpdatesAdapter by inject<TotalUpdatesAdapter>()
    private val countryUpdatesAdapter by inject<CountryUpdatesAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val backward = MaterialSharedAxis.create(MaterialSharedAxis.X, false)
        reenterTransition = backward

        val forward = MaterialSharedAxis.create(MaterialSharedAxis.X, true)
        exitTransition = forward
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val appTheme = context?.getApplicationTheme()
//        if (appTheme != null) {
//            theme_switch.toggleBasedOnTheme(appTheme)
//        }

        val mergeAdapter = MergeAdapter(totalUpdatesAdapter, countryUpdatesAdapter)
        home_data_recycler_view.adapter = mergeAdapter

        swipe_refresh_data_layout.setOnRefreshListener(this)

        getData()
    }

    private fun getData() {
        getGlobalData()
        getMyCountryData()
    }

    private fun getMyCountryData() {
        val myCountryIso = Locale.getDefault().isO3Country
        homeViewModel.getCountryWiseData(myCountryIso).observe(viewLifecycleOwner, Observer {
            swipe_refresh_data_layout.isRefreshing = false
            when (it) {
                is DataResult.Loading -> {
                    showLoading()
                }
                is DataResult.Success -> {
                    val allDataModel = it.data
                    setMyCountryData(allDataModel)
                    showData()
                }
                is DataResult.Failure -> {
                }
            }
        })
    }

    private fun getGlobalData() {
        homeViewModel.getRawData().observe(viewLifecycleOwner, Observer {
            swipe_refresh_data_layout.isRefreshing = false
            when (it) {
                is DataResult.Loading -> {
                    showLoading()
                }
                is DataResult.Success -> {
                    val allDataModel = it.data
                    setData(allDataModel)
                    showData()
                }
                is DataResult.Failure -> {
                }
            }
        })
    }

    private fun setMyCountryData(countryCaseModel: CountryCaseModel?) {
        countryUpdatesAdapter.submitList(listOf(countryCaseModel))
    }

    private fun setData(globalDataModel: GlobalDataModel?) {
        val lastUpdateTime = globalDataModel?.lastUpdate?.let {
            getFormattedDate(
                it,
                "yyyy-MM-dd'T'HH:mm:ss",
                "MMM d, h:mm a"
            )
        }

        text_view_last_update_time.text = "Last updated: $lastUpdateTime"

        totalUpdatesAdapter.submitList(listOf(globalDataModel))
    }

    override fun onRefresh() {
        getData()
    }

    fun showLoading() {
        loading_view.isVisible = true
        container_data_layout.isVisible = false
    }

    fun showData() {
        loading_view.isVisible = false
        container_data_layout.isVisible = true
    }
}