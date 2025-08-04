package com.practise.newsapp.ui.theme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.*

// AppColors.kt
data class CustomColors(
    val textPrimary: Color,
    val border: Color = Color.Black,
    val textFieldBackground: Color,
    val primary: Color = BluePrimary,
    val secondPrimary: Color,
    val buttonTextPrimary: Color,
    val topBar: Color,
    val background: Color = Color.White,
    val surface: Color
)

val LightCustomColors = CustomColors(
    textPrimary = TextPrimaryLight,
    textFieldBackground = TextFieldBackgroundLight,
    secondPrimary = MutedIndigo,
    buttonTextPrimary = Color.White,
    topBar = TopBarLight,
    background = Color.White,
    surface = Color.Black
)

val DarkCustomColors = CustomColors(
    textPrimary = TextPrimaryDark,
    textFieldBackground = TextFieldBackgroundDark,
    secondPrimary = LightGrey,
    buttonTextPrimary = Color.Black,
    topBar = TopBarDark,
    background = Color.Black,
    surface = Color.White
)

val LocalCustomColors = staticCompositionLocalOf { LightCustomColors }
