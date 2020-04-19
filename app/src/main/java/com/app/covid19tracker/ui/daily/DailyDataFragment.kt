package com.app.covid19tracker.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.covid19tracker.R
import com.app.covid19tracker.adapter.DailyUpdatesAdapter
import com.app.covid19tracker.model.DataResult
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.fragment_daily_data.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DailyDataFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    @ExperimentalCoroutinesApi
    private val dailyDataViewModel: DailyDataViewModel by viewModel()
    private val dailyUpdatesAdapter by inject<DailyUpdatesAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val forward = MaterialSharedAxis.create(MaterialSharedAxis.X, true)
        enterTransition = forward

        val backward = MaterialSharedAxis.create(MaterialSharedAxis.X, false)
        returnTransition = backward
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        swipe_refresh_data_layout.setOnRefreshListener(this)

        list_item_daily_data.adapter = dailyUpdatesAdapter

        getData()
    }

    private fun getData() {
        swipe_refresh_data_layout.isRefreshing = false
        dailyDataViewModel.getDailyData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataResult.Loading -> {
                }
                is DataResult.Success -> {
                    val dataList = it.data
                    dailyUpdatesAdapter.submitList(dataList?.asReversed())
                }
                is DataResult.Failure -> {
                }
            }
        })
    }

    override fun onRefresh() {
        getData()
    }
}