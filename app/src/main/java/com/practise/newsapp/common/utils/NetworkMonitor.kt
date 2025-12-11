package com.practise.newsapp.common.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMonitor @Inject constructor(
    @ApplicationContext context: Context
) {
    private val observer = NetworkConnectivityObserver(context)

    val isConnected = observer.isConnected
}
