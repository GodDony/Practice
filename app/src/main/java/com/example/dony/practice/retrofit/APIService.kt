package com.example.dony.practice.retrofit

import com.example.dony.practice.models.OrderBookModel
import com.example.dony.practice.models.TickerModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(APIConsts.PATH_ORDER_BOOK)
    fun requestOrderBook(@Query("currency") currency: String): Observable<OrderBookModel>

    @GET(APIConsts.PATH_TICKER)
    fun requestTicker(@Query("currency") currency: String): Observable<TickerModel>
}
