package com.practise.newsapp

import android.app.Application
import com.practise.newsapp.common.utils.NetworkUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var networkUtils: NetworkUtils
    override fun onCreate() {
        super.onCreate()
        NetworkUtils.init(networkUtils)
    }
}
