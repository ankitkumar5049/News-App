package com.practise.newsapp.ui.theme

import android.app.Activity
import android.os.Build
import android.provider.CalendarContract
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.practise.newsapp.common.dimensions.Dimensions
import com.practise.newsapp.common.dimensions.dimen_4xhdpi
import com.practise.newsapp.common.dimensions.dimen_hdpi
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.dimensions.dimen_xhdpi
import com.practise.newsapp.common.dimensions.dimen_xxhdpi
import com.practise.newsapp.common.dimensions.dimen_xxxhdpi
import com.practise.newsapp.common.utils.Constants.WIDTH_FOUR_EIGHTY
import com.practise.newsapp.common.utils.Constants.WIDTH_FOUR_EIGHTY_ONE
import com.practise.newsapp.common.utils.Constants.WIDTH_ONE_SIXTY
import com.practise.newsapp.common.utils.Constants.WIDTH_ONE_SIXTY_ONE
import com.practise.newsapp.common.utils.Constants.WIDTH_SIX_FORTY
import com.practise.newsapp.common.utils.Constants.WIDTH_THREE_TWENTY
import com.practise.newsapp.common.utils.Constants.WIDTH_THREE_TWENTY_ONE
import com.practise.newsapp.common.utils.Constants.WIDTH_TWO_FORTY
import com.practise.newsapp.common.utils.Constants.WIDTH_TWO_FORTY_ONE
import com.practise.newsapp.common.fontSizes.FontSize
import com.practise.newsapp.common.fontSizes.font_size_4xhdpi
import com.practise.newsapp.common.fontSizes.font_size_hdpi
import com.practise.newsapp.common.fontSizes.font_size_mdpi
import com.practise.newsapp.common.fontSizes.font_size_xhdpi
import com.practise.newsapp.common.fontSizes.font_size_xxhdpi
import com.practise.newsapp.common.fontSizes.font_size_xxxhdpi

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    fontSizes: FontSize,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    val fontSizeSet = remember { fontSizes }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, LocalAppFontSizes provides fontSizeSet, content = content)
}

private val LocalAppDimens = staticCompositionLocalOf {
    dimen_mdpi
}

private val LocalAppFontSizes = staticCompositionLocalOf {
    font_size_mdpi
}
private val DarkColorScheme = darkColorScheme(
    primary = BluePrimary,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    onPrimary = Color.White,
    background = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onPrimary = Color.Black,
    background = Color.White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    var dimensions = dimen_mdpi
    var fontSize = font_size_mdpi
    var typography = newsTypographyDisplay

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val customColors = if (darkTheme) DarkCustomColors else LightCustomColors

    if (configuration.screenWidthDp <= WIDTH_ONE_SIXTY) {
        dimensions = dimen_mdpi
        fontSize = font_size_mdpi
        typography = newsTypographyDisplay
    } else if (configuration.screenWidthDp in (WIDTH_ONE_SIXTY_ONE..WIDTH_TWO_FORTY)) {
        dimensions = dimen_hdpi
        fontSize = font_size_hdpi
        typography = newsTypographyDisplay
    } else if (configuration.screenWidthDp in (WIDTH_TWO_FORTY_ONE..WIDTH_THREE_TWENTY)) {
        dimensions = dimen_xhdpi
        fontSize = font_size_xhdpi
        typography = newsTypographyDisplay
    } else if (configuration.screenWidthDp in (WIDTH_THREE_TWENTY_ONE..WIDTH_FOUR_EIGHTY)) {
        dimensions = dimen_xxhdpi
        fontSize = font_size_xxhdpi
        typography = newsTypographyDisplay
    } else if (configuration.screenWidthDp in (WIDTH_FOUR_EIGHTY_ONE..WIDTH_SIX_FORTY)){
        dimensions = dimen_xxxhdpi
        fontSize = font_size_xxxhdpi
        typography = newsTypographyDisplay
    } else if(configuration.screenWidthDp >= WIDTH_SIX_FORTY){
        dimensions = dimen_4xhdpi
        fontSize = font_size_4xhdpi
        typography = newsTypographyDisplay
    }

    ProvideDimens(
        dimensions = dimensions,
        fontSizes = fontSize
    ) {
        CompositionLocalProvider(LocalCustomColors provides customColors) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = typography,
                content = content
            )
        }
    }

}

object NewsAppTheme {

    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val customColors: CustomColors
        @Composable
        get() = LocalCustomColors.current

    val fontSizes: FontSize
        @Composable
        get() = LocalAppFontSizes.current
}