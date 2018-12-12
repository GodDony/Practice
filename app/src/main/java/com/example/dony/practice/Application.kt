package com.example.dony.practice

import android.app.Application
import com.example.dony.practice.retrofit.ApiController

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        // API 컨트롤러에 사용할 Context 초기화
        ApiController.instance.init(applicationContext)
    }
}
