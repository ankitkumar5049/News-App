package com.practise.newsapp.presentation.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.TransformOrigin

@Composable
fun SlideInHorizontally(
    visible: Boolean,
    fromLeft: Boolean = true,
    enterDuration: Int = 800,
    exitOffset: Int = 200,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            animationSpec = tween(durationMillis = enterDuration)
        ) { fullWidth ->
            if (fromLeft) -fullWidth / 3 else fullWidth / 3
        } + fadeIn(animationSpec = tween(durationMillis = enterDuration)),

        exit = slideOutHorizontally(
            animationSpec = spring(stiffness = Spring.StiffnessHigh)
        ) {
            if (fromLeft) exitOffset else -exitOffset
        } + fadeOut()
    ) {
        content()
    }
}

@Composable
fun SlideInVertically(
    visible: Boolean,
    fromTop: Boolean = true,
    enterDuration: Int = 600,
    exitDuration: Int = 400,
    initialOffsetY: Int = 40,
    initialAlpha: Float = 0.3f,
    targetScale: Float = 1.2f,
    expandFrom: Alignment.Vertical = Alignment.Top,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = enterDuration),
            initialOffsetY = { if (fromTop) -initialOffsetY else initialOffsetY }
        ) +
                expandVertically(
                    expandFrom = expandFrom,
                    animationSpec = tween(durationMillis = enterDuration)
                ) +
                scaleIn(
                    animationSpec = tween(durationMillis = enterDuration),
                    transformOrigin = TransformOrigin(0.5f, if (fromTop) 0f else 1f)
                ) +
                fadeIn(
                    animationSpec = tween(durationMillis = enterDuration),
                    initialAlpha = initialAlpha
                ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = exitDuration)
        ) +
                shrinkVertically(
                    animationSpec = tween(durationMillis = exitDuration)
                ) +
                scaleOut(
                    animationSpec = tween(durationMillis = exitDuration),
                    targetScale = targetScale
                ) +
                fadeOut(
                    animationSpec = tween(durationMillis = exitDuration)
                )
    ) {
        content()
    }
}
