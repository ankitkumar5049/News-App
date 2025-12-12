package com.practise.newsapp.presentation.uiComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.ui.theme.NewsAppTheme.customColors
import com.practise.newsapp.ui.theme.NewsAppTheme.dimens

@Composable
fun CommonButton(
    modifier: Modifier,
    buttonModifier: Modifier,
    buttonIcon: Painter? = null,
    text: String = Constants.EMPTY_STRING,
    textResource: Int = 0,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    elevation: ButtonElevation? = null,
    contentPadding: PaddingValues = PaddingValues(
        start = dimens.x_5_dp,
        end = dimens.x_5_dp,
        top = dimens.x_16_dp,
        bottom = dimens.x_16_dp,
    ),
    shape: Shape = RoundedCornerShape(dimens.x_4_dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        contentColor = customColors.buttonTextPrimary,
        containerColor = customColors.primary,
        disabledContentColor = customColors.buttonTextPrimary.copy(alpha = 0.6f),
        disabledContainerColor = customColors.primary.copy(alpha = 0.6f)
    ),
    maxLine: Int = Constants.MAX_LINE,
    buttonContent: String = Constants.EMPTY_STRING,
    onClick: () -> Unit
){

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Button(
        onClick = {
            keyboardController?.hide()
            focusManager.clearFocus()
            onClick()
        },
        shape = shape,
        modifier = modifier.then(buttonModifier),
        enabled = enabled,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        colors = colors
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    maxLines = maxLine,
                    modifier = Modifier.semantics { contentDescription = buttonContent }
                )
            }
        }
    }
}