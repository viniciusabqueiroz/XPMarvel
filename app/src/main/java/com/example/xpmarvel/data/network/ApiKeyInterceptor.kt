package com.example.xpmarvel.data.network

import android.util.Log
import com.example.xpmarvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.PUBLIC_KEY)
            .build()
        Log.d("key interceptor", url.toString())
        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY = "apikey"
    }
}