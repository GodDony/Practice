package com.example.dony.practice.architectures.livemodels

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import com.example.dony.practice.architectures.bases.BaseViewModel
import com.example.dony.practice.models.OrderBookModel
import com.example.dony.practice.retrofit.ApiController

class OrderBookViewModel : BaseViewModel() {
    val orderBookLiveData = MutableLiveData<OrderBookModel>()

    @SuppressLint("CheckResult")
    fun requestOrderBook(currency: String) {
        ApiController.instance.requestOrderBook(currency).subscribe({ orderBookLiveData.value = it }) { onErrorHandle(null, it, null) }
    }

    override fun onErrorHandle(activity: Activity?, error: Throwable, errorUnit: (() -> Unit)?) {
        // errorCode 이외의 에러처리
    }
}
