package com.example.dony.practice.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.example.dony.practice.R
import com.example.dony.practice.adapters.OrderBookListAdapter
import com.example.dony.practice.architectures.livemodels.OrderBookViewModel
import com.example.dony.practice.bases.BaseActivity
import com.example.dony.practice.utils.OrderBookComparator
import com.example.dony.practice.utils.gson.CommonUtils
import kotlinx.android.synthetic.main.activity_order_book.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.*

class OrderBookActivity : BaseActivity() {
    private lateinit var orderBookViewModel: OrderBookViewModel
    private var currency = ""
    private var isRefresh = true
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_book)

        supportActionBar?.run {
            hide()
        }

        onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.let {
            currency = if (it.hasExtra("currency")) it.getStringExtra("currency") else ""
            val price = if (it.hasExtra("price")) it.getStringExtra("price") else ""
            val previousPrice = if (it.hasExtra("previous_price")) it.getStringExtra("previous_price") else ""
            val highPrice = if (it.hasExtra("high_price")) it.getStringExtra("high_price") else ""
            val lowPrice = if (it.hasExtra("low_price")) it.getStringExtra("low_price") else ""
            val transactionPrice = if (it.hasExtra("transaction_price")) it.getStringExtra("transaction_price") else ""

            if (currency.isEmpty() || price.isEmpty() || previousPrice.isEmpty() || highPrice.isEmpty() || lowPrice.isEmpty()) {
                finish()
                return
            }

            txt_title.text = currency
            txt_price.text = price
            txt_previous_day_price.text = previousPrice
            txt_high_price.text = highPrice
            txt_low_price.text = lowPrice
            txt_transaction_price.text = transactionPrice

            recycler_view.run {
                adapter = OrderBookListAdapter()
            }

            orderBookViewModel = ViewModelProviders.of(this).get(OrderBookViewModel::class.java).apply {
                orderBookLiveData.observe(this@OrderBookActivity, Observer { orderBookModel ->
                    orderBookModel?.run {
                        onCommonError(result, errorCode)
                        // 현재가
                        CommonUtils.getThousandsFormat(ask[0].price).let { price ->
                            txt_price.text = price
                            txt_current_price.text = price
                            // 전일대비
                            (CommonUtils.getStringToLong(price) - CommonUtils.getStringToLong(previousPrice)).let { before_price ->
                                txt_day_before_price.text = String.format(getString(R.string.term_minus), CommonUtils.getThousandsFormat(before_price))
                            }
                        }
                        Collections.sort(ask, OrderBookComparator(true))
                        Collections.sort(bid, OrderBookComparator(false))
                        (recycler_view.adapter as OrderBookListAdapter).putItems(ask,bid)
                    }
                })
                requestOrderBook(currency)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isRefresh = true
        job?.start().also {
            job = launch(UI) {
                while (isRefresh) {
                    delay(5000)
                    orderBookViewModel.requestOrderBook(currency)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isRefresh = false
        job?.cancel()
    }
}
