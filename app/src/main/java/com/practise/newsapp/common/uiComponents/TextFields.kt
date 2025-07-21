package com.practise.newsapp.common.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonUtilities
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.ui.theme.BluePrimary
import com.practise.newsapp.ui.theme.NewsAppTheme
import com.practise.newsapp.ui.theme.NewsAppTheme.customColors
import com.practise.newsapp.ui.theme.NewsAppTheme.dimens
import com.practise.newsapp.ui.theme.OsloGray


@Composable
fun CommonTextInputFields(
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    enabled: Boolean = true,
    value: String,
    maxChar: Int,
    borderColor: Color = Color.Black,
    onValueChange: (newValue: String) -> Unit,
    onFocusedChange: ((FocusState) -> Unit)? = null,
    labelString: String = Constants.EMPTY_STRING,
    labelTextColor: Color? = null,
    labelResource: Int = 0,
    backgroundColor: Color = customColors.textFieldBackground,
    useDisabledColorsOnly: Boolean = false,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    errorText: String = Constants.EMPTY_STRING,
    errorTextColor: Color = MaterialTheme.colorScheme.error,
    isSensitiveInformation: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enforceVisualTransformation: Boolean = false,
    semanticName: String,
    labelAsteriskRequired: Boolean = false,
    textFieldColors: TextFieldColors = if (useDisabledColorsOnly) {
        TextFieldDefaults.colors(
            disabledTextColor = OsloGray,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = BluePrimary,
            errorCursorColor = BluePrimary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = OsloGray
        )
    } else {
        TextFieldDefaults.colors(
            disabledTextColor = Color.Black,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = BluePrimary,
            errorCursorColor = BluePrimary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = OsloGray
        )
    }
){
    val keyboardController = LocalSoftwareKeyboardController.current
    var isFocused by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf(errorText) }

    if(isSensitiveInformation){
        CommonUtilities.DisableScreenCapture()
    }

    Row(modifier = rowModifier) {
        Column {
            Text(
                buildAnnotatedString {
                    append(labelString)
                    if (labelAsteriskRequired) {
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("*")
                        }
                    }
                },
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = NewsAppTheme.fontSizes.x_1_25,
                ),
                color = labelTextColor ?: customColors.secondPrimary
            )


            Spacer(Modifier.height(dimen_mdpi.x_0_75))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                        onFocusedChange?.invoke(it)
                    }
                    .clip(RoundedCornerShape(dimens.x_4_dp))
                    .background(backgroundColor)
                    .border(
                        width = dimens.x_1_dp,
//                        color = borderColor
//                            ?: if (isFocused) customColors.border else Color.Black,
                        color = borderColor,
                        shape = RoundedCornerShape(dimens.x_4_dp)
                    )
                    .then(modifier)
                    .semantics { contentDescription = semanticName },
                enabled = enabled,
                value = value,
                onValueChange = {
                    if (it.length <= maxChar) {
                        errorMsg = Constants.EMPTY_STRING
                        onValueChange(CommonUtilities.trimSpaces(it))
                    }
                },
                maxLines = maxLines,
                singleLine = singleLine,
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
                isError = errorMsg.isNotEmpty(),
                colors = textFieldColors,
                visualTransformation = if (enforceVisualTransformation || !isFocused) visualTransformation else VisualTransformation.None
//                label = {
//                    if(labelAsteriskRequired){
//                        LabelWithAsterisk(
//                            labelString = labelString,
//                            labelId = labelResource,
//                            value = value,
//                            labelTextColor = labelTextColor,
//                            isFocused = isFocused
//                        )
//                    }else{
//                        Text(
//                            labelString.ifEmpty { stringResource(id = labelResource) },
//                            style = LocalTextStyle.current.copy(
//                                fontWeight = FontWeight.Normal,
//                                fontSize = if (value.isNotEmpty() || isFocused) NewsAppTheme.fontSizes.x_0_80 else NewsAppTheme.fontSizes.x_1_2,
//                                color = labelTextColor
//                                    ?: if ((value.isEmpty() && !isFocused) || useDisabledColorsOnly) {
//                                        Color.Black
//                                    } else {
//                                        MaterialTheme.colorScheme.primary
//                                    }
//                            )
//                        )
//                    }
//                }

            )

            if (errorText.isNotEmpty()) {
                Text(
                    buildAnnotatedString {
//                        if (errorMsg.isNotEmpty()) {
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                append("!")
                                append(errorText)
                            }
//                        }

                    },
                    modifier = Modifier.padding(top = dimen_mdpi.x_0_75),
                    style = LocalTextStyle.current.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = NewsAppTheme.fontSizes.x_1_25,
                    ),
                    color = errorTextColor,
                )
            }
        }
    }
}

@Composable
fun HeadingText(
    inputText: String,
    modifier: Modifier = Modifier,
    textColor: Color,
){
    Text(
        text = inputText,
        style = MaterialTheme.typography.displayLarge.copy(
            fontWeight = FontWeight.Bold,
        ),
        color = textColor,
        modifier = modifier
    )
}

@Composable
fun SubHeadingText(
    inputText: String,
    modifier: Modifier = Modifier,
    textColor: Color = customColors.secondPrimary
){
    Text(
        text = inputText,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Normal,
        ),
        color = textColor,
        modifier = modifier,
    )
}

@Composable
fun CommonTextField(
    inputText: String,
    clickable: Boolean = false,
    link: String? = null,
    isLink: Boolean = false,
    modifier: Modifier,
    contentColor: Color = customColors.textPrimary
){
    Text(
        text = inputText,
        color = if(isLink) customColors.primary else contentColor,
        textAlign = TextAlign.Center,
        style = LocalTextStyle.current.copy(
            fontWeight = FontWeight.Normal,
            fontSize = NewsAppTheme.fontSizes.x_1_25,
        ),
        modifier = if(clickable) Modifier.clickable {

        } else Modifier.then(modifier)
    )
}

@Composable
fun SubtitleText(
    modifier: Modifier = Modifier,
    align: TextAlign = TextAlign.Center,
    color: Color = NewsAppTheme.customColors.primary,
    text: String = "",
    ellipsis: Boolean = false,
    lineHeight: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Normal,
    style: TextStyle = MaterialTheme.typography.headlineSmall,
    textDecoration: TextDecoration? = null
) {
    Text(
        textAlign = align,
        text = text,
        color = color,
        style = style,
        overflow = if (ellipsis) TextOverflow.Ellipsis else TextOverflow.Clip,
        maxLines = if (ellipsis) 1 else Int.MAX_VALUE,
        lineHeight = lineHeight,
        fontWeight = fontWeight,
        modifier = modifier,
        textDecoration =textDecoration
    )
}
