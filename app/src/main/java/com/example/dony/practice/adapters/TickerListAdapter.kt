package com.example.dony.practice.adapters

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dony.practice.R
import com.example.dony.practice.activities.OrderBookActivity
import com.example.dony.practice.adapters.viewholder.SimpleViewHolder
import com.example.dony.practice.models.TickerModel
import com.example.dony.practice.utils.gson.CommonUtils
import kotlinx.android.synthetic.main.list_item_ticker_title.view.*

class TickerListAdapter : RecyclerView.Adapter<SimpleViewHolder>() {
    private val tickerTitles = ArrayList<TickerModel.TickerTitleModel>()

    fun putItems(items: ArrayList<TickerModel.TickerTitleModel>) {
        tickerTitles.clear()
        tickerTitles.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_ticker_title, parent, false))
    }

    override fun getItemCount(): Int {
        return tickerTitles.size
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.itemView.run {
            tickerTitles[holder.adapterPosition].run {
                txt_currency.text = currency
                txt_price.text = CommonUtils.getThousandsFormat(last)
                txt_price_count.text = CommonUtils.getTransactionPrice(context, last, volume)

                setOnClickListener {
                    context.startActivity(
                            Intent(context, OrderBookActivity::class.java).apply {
                                putExtra("currency", currency)
                                putExtra("price", txt_price.text.toString())
                                putExtra("previous_price", CommonUtils.getThousandsFormat(yesterday_last))
                                putExtra("high_price", CommonUtils.getThousandsFormat(high))
                                putExtra("low_price", CommonUtils.getThousandsFormat(low))
                                putExtra("transaction_price", txt_price_count.text.toString())
                            },
                            ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity,
                                    Pair.create(txt_currency, context.getString(R.string.transition_name_title))).toBundle()
                    )
                }
            }
        }
    }
}