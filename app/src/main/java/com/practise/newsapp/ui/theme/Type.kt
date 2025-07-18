package com.practise.newsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.practise.newsapp.R


val Poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val newsTypographyDisplay = Typography(
    displayLarge = TextStyle( // Large
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 72.sp
    ),
    displayMedium = TextStyle( // Medium
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 48.sp
    ),
    displaySmall = TextStyle( // Small
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 36.sp
    ),
    headlineLarge = TextStyle( // Large Bold
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 72.sp
    ),
    headlineMedium = TextStyle( // Medium Bold
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 48.sp
    ),
    headlineSmall = TextStyle( // Small Bold
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 36.sp
    )
)


val neoTypographyMDPI = Typography(
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 6.sp,
        lineHeight = 8.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 4.66.sp,
        lineHeight = 6.66.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 4.sp,
        lineHeight = 5.33.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 5.33.sp,
        lineHeight = 8.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 5.33.sp,
        lineHeight = 8.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 6.66.sp,
        lineHeight = 9.33.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 4.sp,
        lineHeight = 5.33.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontSize = 3.33.sp,
        lineHeight = 4.sp,
        fontWeight = FontWeight.Light
    )
)


val neoTypographyHDPI = Typography(
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 9.sp,
        lineHeight = 12.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 7.sp,
        lineHeight = 10.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 6.sp,
        lineHeight = 8.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 8.sp,
        lineHeight = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 12.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 6.sp,
        lineHeight = 8.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 5.sp,
        lineHeight = 6.sp
    )
)


val neoTypographyXHDPI = Typography(
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 9.33.sp,
        lineHeight = 13.33.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 10.66.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.66.sp,
        lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 10.66.sp,
        lineHeight = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 13.33.sp,
        lineHeight = 18.66.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp,
        lineHeight = 10.66.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 6.66.sp,
        lineHeight = 8.sp
    )
)


val neoTypographyXXHDPI = Typography(
    displayLarge = TextStyle( // Maps from h2
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 48.sp
    ),
    displaySmall = TextStyle( // Maps from h4
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    headlineMedium = TextStyle( // subtitle1
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    headlineSmall = TextStyle( // subtitle2
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyLarge = TextStyle( // body1
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    bodyMedium = TextStyle( // body2
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelLarge = TextStyle( // button
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelMedium = TextStyle( // overline
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    labelSmall = TextStyle( // caption
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    titleSmall = TextStyle( // h6
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp,
        lineHeight = 12.sp
    )
)


val neoTypographyXXXHDPI = Typography(
    displayLarge = TextStyle( // from h2
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 64.sp
    ),
    headlineMedium = TextStyle( // from subtitle1
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    headlineSmall = TextStyle( // from subtitle2
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 18.64.sp,
        lineHeight = 26.64.sp
    ),
    bodyLarge = TextStyle( // from body1
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 21.32.sp
    ),
    bodyMedium = TextStyle( // from body2
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 21.32.sp,
        lineHeight = 32.sp
    ),
    labelLarge = TextStyle( // from button
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 21.32.sp,
        lineHeight = 32.sp
    ),
    labelMedium = TextStyle( // from overline
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 26.64.sp,
        lineHeight = 37.32.sp
    ),
    labelSmall = TextStyle( // from caption
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 21.32.sp
    ),
    titleSmall = TextStyle( // from h6
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 13.32.sp,
        lineHeight = 16.sp
    )
)

val neoTypography4xHDPI = Typography(
    displayLarge = TextStyle( // h2
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 72.sp,
        lineHeight = 96.sp
    ),
    headlineMedium = TextStyle( // subtitle1
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 48.sp
    ),
    headlineSmall = TextStyle( // subtitle2
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 40.sp
    ),
    bodyLarge = TextStyle( // body1
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    bodyMedium = TextStyle( // body2
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 48.sp
    ),
    labelLarge = TextStyle( // button
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 48.sp
    ),
    labelMedium = TextStyle( // overline
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 56.sp
    ),
    labelSmall = TextStyle( // caption
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleSmall = TextStyle( // h6
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        lineHeight = 24.sp
    )
)