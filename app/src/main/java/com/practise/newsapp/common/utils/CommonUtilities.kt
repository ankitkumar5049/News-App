package com.practise.newsapp.common.utils

import android.app.Activity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

object CommonUtilities {

    @Composable
    fun DisableScreenCapture(){
        val context = LocalContext.current as AppCompatActivity
        val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(
            key1 = lifecycleOwner, effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_RESUME) {
                        disableScreenCapture(context)
                    }
                    else if (event == Lifecycle.Event.ON_STOP) {
                        enableScreenCapture(context)
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)

                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }
        )
    }

    fun disableScreenCapture(activity: AppCompatActivity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }

    fun enableScreenCapture(activity: AppCompatActivity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    fun trimStartSpaces(input: String): String {
        return input.trimStart()
    }
}