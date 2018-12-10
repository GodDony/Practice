package com.example.dony.practice.utils

import com.example.dony.practice.models.OrderBookModel

class OrderBookComparator constructor(private val isAsk: Boolean) : Comparator<OrderBookModel.OrderBookPriceModel> {
    override fun compare(arg0: OrderBookModel.OrderBookPriceModel, arg1: OrderBookModel.OrderBookPriceModel): Int {
        return if (isAsk) arg0.price.compareTo(arg1.price) else arg1.price.compareTo(arg0.price)
    }
}