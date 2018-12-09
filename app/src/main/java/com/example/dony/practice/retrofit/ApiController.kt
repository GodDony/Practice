package com.example.dony.practice.retrofit

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import com.example.dony.practice.models.OrderBookModel
import com.example.dony.practice.models.TickerModel
import com.example.dony.practice.retrofit.bases.BaseAPIController
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class ApiController : BaseAPIController() {

    /**
     * 오더북 가져오기
     */
    fun requestOrderBook(currency: String): Observable<OrderBookModel> {
        return getCoinOneService().requestOrderBook(currency)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 코인 증권 시세 가져오기
     */
    fun requestTicker(currency: String): Observable<TickerModel> {
        return getCoinOneService().requestTicker(currency)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private object Holder {
        @SuppressLint("StaticFieldLeak")
        val INSTANCE = ApiController()
    }

    companion object {
        val instance: ApiController by lazy { Holder.INSTANCE }
    }
}
