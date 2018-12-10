package com.example.dony.practice.models

class OrderBookModel {
    var result = ""
    var errorCode = ""
    var timestamp: Long = 0
    var currency = ""
    var ask = ArrayList<OrderBookPriceModel>()
    var bid = ArrayList<OrderBookPriceModel>()

    inner class OrderBookPriceModel {
        var price: Long = 0
        var qty: Double = 0.0
    }
}
