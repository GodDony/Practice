package com.example.dony.practice.bases

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.dony.practice.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    /**
     * 코인원 API 공통 에러 처리
     */
    fun onCommonError(result: String?, errorCode: String?): Boolean {
        result?.let {
            if (it == "error") {
                errorCode?.let {errorCode ->
                    val errorMsg = getString(when (errorCode) {
                        "4" -> R.string.error_code_4
                        "11" -> R.string.error_code_11
                        "12" -> R.string.error_code_12
                        "40" -> R.string.error_code_40
                        "50" -> R.string.error_code_50
                        "51" -> R.string.error_code_51
                        "52" -> R.string.error_code_52
                        "53" -> R.string.error_code_53
                        "100" -> R.string.error_code_100
                        "101" -> R.string.error_code_101
                        "102" -> R.string.error_code_102
                        "103" -> R.string.error_code_103
                        "104" -> R.string.error_code_104
                        "105" -> R.string.error_code_105
                        "106" -> R.string.error_code_106
                        "107" -> R.string.error_code_107
                        "111" -> R.string.error_code_111
                        "112" -> R.string.error_code_112
                        "113" -> R.string.error_code_113
                        "120" -> R.string.error_code_120
                        "121" -> R.string.error_code_121
                        "122" -> R.string.error_code_122
                        "123" -> R.string.error_code_123
                        "130" -> R.string.error_code_130
                        "131" -> R.string.error_code_131
                        "132" -> R.string.error_code_132
                        "141" -> R.string.error_code_141
                        "150" -> R.string.error_code_150
                        "151" -> R.string.error_code_151
                        "200" -> R.string.error_code_200
                        "202" -> R.string.error_code_202
                        "210" -> R.string.error_code_210
                        "220" -> R.string.error_code_220
                        "221" -> R.string.error_code_221
                        "310" -> R.string.error_code_310
                        "311" -> R.string.error_code_311
                        "312" -> R.string.error_code_312
                        "330" -> R.string.error_code_330
                        "404" -> R.string.error_code_404
                        "405" -> R.string.error_code_405
                        "444" -> R.string.error_code_444
                        "500" -> R.string.error_code_500
                        "501" -> R.string.error_code_501
                        "777" -> R.string.error_code_777
                        "778" -> R.string.error_code_778
                        "779" -> R.string.error_code_779
                        "1202" -> R.string.error_code_1202
                        "1203" -> R.string.error_code_1203
                        "1204" -> R.string.error_code_1204
                        "1205" -> R.string.error_code_1205
                        "1206" -> R.string.error_code_1206
                        "1207" -> R.string.error_code_1207
                        "1208" -> R.string.error_code_1208
                        "1209" -> R.string.error_code_1209
                        else -> R.string.error_code_none
                    })
                    Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
                }
                return true
            }
        }
        return false
    }
}