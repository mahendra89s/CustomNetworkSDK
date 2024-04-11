package com.example.networksdk.data

import javax.inject.Inject

class MovieNetworkSDK @Inject constructor() {
    companion object {
        var API_KEY = ""
        var BASE_URL = ""
        fun initailize(apiKey: String, baseUrl: String){
            API_KEY = apiKey
            BASE_URL = baseUrl
        }
    }

}