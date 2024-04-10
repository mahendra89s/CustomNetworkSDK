package com.example.networksdk.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            val url = chain.request().url.newBuilder().addQueryParameter(
                "api_key", "909594533c98883408adef5d56143539"
            ).build()
            url(url)
        }
        return chain.proceed(request.build())
    }
}