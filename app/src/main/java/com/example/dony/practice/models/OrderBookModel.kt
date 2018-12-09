package com.example.dony.practice.models

class OrderBookModel {
    var result = ""
    var errorCode = ""
    var timestamp: Long = 0
    var currency = ""
    var ask = ArrayList<Ask>()
    var bid = ArrayList<Bid>()

    inner class Ask {
        var price: Long = 0
        var qty: Double = 0.0
    }

    inner class Bid {
        var price: Long = 0
        var qty: Double = 0.0
    }
}
