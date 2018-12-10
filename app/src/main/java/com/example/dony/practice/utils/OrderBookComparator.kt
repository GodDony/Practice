package com.example.dony.practice.utils

import com.example.dony.practice.models.OrderBookModel

class OrderBookComparator : Comparator<OrderBookModel.OrderBookPriceModel> {
    override fun compare(arg0: OrderBookModel.OrderBookPriceModel, arg1: OrderBookModel.OrderBookPriceModel): Int {
        return arg1.price.compareTo(arg0.price)
    }
}