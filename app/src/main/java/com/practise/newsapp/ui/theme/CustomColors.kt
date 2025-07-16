package com.practise.newsapp.ui.theme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.*

// AppColors.kt
data class CustomColors(
    val textPrimary: Color,
    val border: Color,
    val textFieldBackground: Color,
    val primary: Color = BluePrimary,
)

val LightCustomColors = CustomColors(
    textPrimary = TextPrimaryLight,
    border = BorderLight,
    textFieldBackground = TextFieldBackgroundLight
)

val DarkCustomColors = CustomColors(
    textPrimary = TextPrimaryDark,
    border = BorderDark,
    textFieldBackground = TextFieldBackgroundDark
)

val LocalCustomColors = staticCompositionLocalOf { LightCustomColors }
