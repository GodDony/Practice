package com.example.dony.practice.models

class TickerModel {
    var result = ""
    var errorCode = ""
    var timestamp: Long = 0
    var btc = TickerTitleModel()
    var bch = TickerTitleModel()
    var eth = TickerTitleModel()
    var etc = TickerTitleModel()
    var xrp = TickerTitleModel()
    var qtum = TickerTitleModel()
    var iota = TickerTitleModel()
    var ltc = TickerTitleModel()
    var btg = TickerTitleModel()
    var omg = TickerTitleModel()
    var eos = TickerTitleModel()
    var data = TickerTitleModel()
    var zil = TickerTitleModel()
    var knc = TickerTitleModel()
    var zrx = TickerTitleModel()
    var xtz = TickerTitleModel()

    fun getTitleModels(): ArrayList<TickerTitleModel> {
        return ArrayList<TickerTitleModel>().apply {
            add(btc)
            add(bch)
            add(eth)
            add(etc)
            add(xrp)
            add(qtum)
            add(iota)
            add(ltc)
            add(btg)
            add(omg)
            add(eos)
            add(data)
            add(zil)
            add(knc)
            add(zrx)
            add(xtz)
        }
    }

    inner class TickerTitleModel {
        var high: Long = 0
        var low: Long = 0
        var last: Long = 0
        var first: Long = 0
        var volume: Double = 0.0
        var yesterday_high: Long = 0
        var yesterday_low: Long = 0
        var yesterday_last: Long = 0
        var yesterday_first: Long = 0
        var yesterday_volume: Double = 0.0
        var currency = ""
    }
}
