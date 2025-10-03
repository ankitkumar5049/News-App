package com.practise.newsapp.presentation.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.ui.theme.NewsAppTheme

@Composable
fun WaitScreen(
    message: String = CommonString.PLEASE_WAIT,
    isBackButtonEnabled: Boolean = false
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black.copy(alpha = 0.7f)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.background(
                NewsAppTheme.customColors.textFieldBackground
            )
        ) {
            LogoPulseLoader()
            SubtitleText(
                text = message,
            )
        }
    }
}