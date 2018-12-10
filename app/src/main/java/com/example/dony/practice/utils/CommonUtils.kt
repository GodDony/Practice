package com.example.dony.practice.utils.gson

import android.content.Context
import com.example.dony.practice.R
import java.text.DecimalFormat

object CommonUtils {
    private val df = DecimalFormat("#,###")

    fun getStringToLong(string: String): Long {
        return string.replace(",", "").toLong()
    }

    /**
     * 1000단위 콤마
     */
    fun getThousandsFormat(number: Number): String {
        return if (number.toLong() >= 1000) {
            df.format(number)
        } else {
            number.toString()
        }
    }

    /**
     * 거래 대금 계산
     * (거래대금 계산 개념을 몰라서 거래량 * 금액으로 계산)
     */
    fun getTransactionPrice(context: Context, last: Long, volume: Double): String {
        (last * volume).let {
            return if (1000000 <= it) {
                String.format(context.getString(R.string.term_million), df.format(it / 1000000))
            } else {
                df.format(it)
            }
        }
    }
}