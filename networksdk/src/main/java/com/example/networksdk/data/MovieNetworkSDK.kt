package com.example.networksdk.data

import android.content.Context
import android.content.pm.PackageManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieNetworkSDK {
    private var context: Context? = null

    operator fun invoke(mContext: Context) {
        context = mContext
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val apiKeyInterceptor by lazy{
        Interceptor {
            val request = it.request().newBuilder().apply {
                val url = it.request().url.newBuilder().addQueryParameter(
                    "api_key", getApiKey()
                ).build()
                url(url)
            }
            return@Interceptor it.proceed(request.build())
        }
    }

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .addInterceptor(apiKeyInterceptor)
        .build()

    private fun getBaseUrl(): String {
        val applicationInfo = context?.packageManager?.getApplicationInfo(
            context?.packageName!!,
            PackageManager.GET_META_DATA
        )
        val bundle = applicationInfo?.metaData
        return bundle?.getString("com.customsdk.network.baseurl", "no-host") ?: "no-host"
    }
    private fun getApiKey(): String{
        val applicationInfo = context?.packageManager?.getApplicationInfo(
            context?.packageName!!,
            PackageManager.GET_META_DATA
        )
        val bundle = applicationInfo?.metaData
        return bundle?.getString("com.customsdk.network.apikey", "no-host") ?: "no-host"
    }
}