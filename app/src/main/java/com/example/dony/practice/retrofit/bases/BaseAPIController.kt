package com.example.dony.practice.retrofit.bases

import android.content.Context
import com.apposter.watchlib.utils.gson.DateDeserializer
import com.example.dony.practice.retrofit.APIConsts
import com.example.dony.practice.retrofit.APIService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

abstract class BaseAPIController {

    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    /**
     * 로그를 찍기 위한 HttpLoggingInterceptor 를 가져온다.
     */
    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    /**
     * 코인원에 사용할 OkHttpClient 를 가져온다.
     */
    private val okHttpClient: OkHttpClient
        get() {
            val okHttpBuilder = OkHttpClient().newBuilder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(25, TimeUnit.SECONDS)
            context?.let {
                // 디버깅 모드에서만 Http 로그를 찍는다.
                okHttpBuilder.addInterceptor(httpLoggingInterceptor)
            }

            return okHttpBuilder.build()
        }

    /**
     * Gson Converter 를 사용하는 Retrofit 2 를 가져온다.
     *
     * @return Retrofit
     */
    private fun getRetrofitWithGsonConverter(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(APIConsts.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(Date::class.java, DateDeserializer()).create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    /**
     * APIService 가져오기
     *
     * @return APIService
     */
    fun getCoinOneService(): APIService {
        return getRetrofitWithGsonConverter(okHttpClient).create(APIService::class.java)
    }
}
