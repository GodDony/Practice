package com.example.dony.practice.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dony.practice.R
import com.example.dony.practice.adapters.viewholder.SimpleViewHolder
import com.example.dony.practice.models.OrderBookModel
import com.example.dony.practice.utils.OrderBookComparator
import com.example.dony.practice.utils.gson.CommonUtils
import kotlinx.android.synthetic.main.list_item_order_book.view.*
import java.util.*

class OrderBookListAdapter : RecyclerView.Adapter<SimpleViewHolder>() {
    private val orderBooks = ArrayList<OrderBookModel.OrderBookPriceModel>()
    private val asks = ArrayList<OrderBookModel.OrderBookPriceModel>()
    private val bids = ArrayList<OrderBookModel.OrderBookPriceModel>()

    fun putItems(askItems: ArrayList<OrderBookModel.OrderBookPriceModel>, bidItems: ArrayList<OrderBookModel.OrderBookPriceModel>) {
        asks.clear()
        bids.clear()
        orderBooks.clear()

        if (askItems.size >= 10) {
            for (i in askItems.size - 10 until askItems.size) {
                asks.add(askItems[i])
            }
        } else {
            asks.addAll(askItems)
        }

        if (bidItems.size >= 10) {
            for (i in 0 until 10) {
                bids.add(bidItems[i])
            }
        } else {
            bids.addAll(bidItems)
        }

        orderBooks.addAll(asks)
        orderBooks.addAll(bids)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_order_book, parent, false))
    }

    override fun getItemCount(): Int {
        return orderBooks.size
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.itemView.run {
            orderBooks[holder.adapterPosition].run {
                if (asks.size > holder.adapterPosition) {
                    txt_price.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                    txt_price.text = CommonUtils.getThousandsFormat(price)
                    txt_count.text = CommonUtils.getThousandsFormat(qty)
                } else {
                    txt_price.setTextColor(ContextCompat.getColor(context, R.color.coral_pink))
                    txt_price.text = CommonUtils.getThousandsFormat(price)
                    txt_count.text = CommonUtils.getThousandsFormat(qty)
                }
            }
        }
    }
}