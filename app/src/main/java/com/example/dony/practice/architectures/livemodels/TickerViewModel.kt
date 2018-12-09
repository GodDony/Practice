package com.example.dony.practice.architectures.livemodels

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import com.example.dony.practice.architectures.bases.BaseViewModel
import com.example.dony.practice.models.TickerModel
import com.example.dony.practice.retrofit.ApiController

class TickerViewModel : BaseViewModel() {
    val ticerLiveModel = MutableLiveData<TickerModel>()

    @SuppressLint("CheckResult")
    fun requestTicker(currency: String) {
        ApiController.instance.requestTicker(currency).subscribe({ ticerLiveModel.value = it }) { onErrorHandle(null, it, null) }
    }

    override fun onErrorHandle(activity: Activity?, error: Throwable, errorUnit: (() -> Unit)?) {
        // errorCode 이외의 에러처리
    }
}