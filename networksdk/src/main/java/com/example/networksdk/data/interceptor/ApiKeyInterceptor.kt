package com.example.networksdk.data.interceptor

import com.example.networksdk.data.MovieNetworkSDK
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            val url = chain.request().url.newBuilder().addQueryParameter(
                "api_key", MovieNetworkSDK.API_KEY
            ).build()
            url(url)
        }
        return chain.proceed(request.build())
    }
}