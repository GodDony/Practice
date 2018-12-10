package com.example.dony.practice.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.dony.practice.R
import com.example.dony.practice.adapters.TickerListAdapter
import com.example.dony.practice.architectures.livemodels.TickerViewModel
import com.example.dony.practice.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_ticker.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class TickerActivity : BaseActivity() {
    private lateinit var tickerViewModel: TickerViewModel
    private var isRefresh = true
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticker)

        supportActionBar?.run {
            hide()
        }

        tickerViewModel = ViewModelProviders.of(this).get(TickerViewModel::class.java).apply {
            ticerLiveModel.observe(this@TickerActivity, Observer {
                refresh_layout.isRefreshing = false
                it?.run {
                    onCommonError(result, errorCode)
                    (recycler_view.adapter as TickerListAdapter).putItems(getTitleModels())
                }
            })
        }

        refresh_layout.setOnRefreshListener {
            tickerViewModel.requestTicker("all")
        }

        recycler_view.run {
            adapter = TickerListAdapter()
        }
        requestTicker()
    }

    override fun onResume() {
        super.onResume()
        isRefresh = true
        job?.start().also {
            job = launch(UI) {
                while (isRefresh) {
                    delay(5000)
                    requestTicker()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isRefresh = false
        job?.cancel()
    }

    private fun requestTicker() {
        refresh_layout.isRefreshing = true
        tickerViewModel.requestTicker("all")
    }
}
