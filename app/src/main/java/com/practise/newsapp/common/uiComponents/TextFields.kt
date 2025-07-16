package com.practise.newsapp.common.uiComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.practise.newsapp.common.utils.CommonUtilities
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.ui.theme.BluePrimary
import com.practise.newsapp.ui.theme.NewsAppTheme
import com.practise.newsapp.ui.theme.NewsAppTheme.customColors
import com.practise.newsapp.ui.theme.NewsAppTheme.dimens
import com.practise.newsapp.ui.theme.OsloGray
import com.practise.newsapp.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextInputFields(
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    enabled: Boolean = true,
    value: String,
    maxChar: Int,
    borderColor: Color? = null,
    onValueChange: (newValue: String) -> Unit,
    onFocusedChange: ((FocusState) -> Unit)? = null,
    labelString: String = Constants.EMPTY_STRING,
    labelTextColor: Color? = null,
    labelResource: Int = 0,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    useDisabledColorsOnly: Boolean = false,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    errorText: String = Constants.EMPTY_STRING,
    errorTextColor: Color = MaterialTheme.colorScheme.error,
    isSensitiveInformation: Boolean = false,
    semanticName: String,
    labelAsteriskRequired: Boolean = false,
    textFieldColors: TextFieldColors = if(useDisabledColorsOnly){
        TextFieldDefaults.textFieldColors(
            disabledTextColor = OsloGray,
//            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = BluePrimary,
            errorCursorColor = BluePrimary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = OsloGray
        )
    }else{
        TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Black,
//            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
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
                text = labelString
            )

            Spacer(Modifier.height(2.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                        onFocusedChange?.invoke(it)
                    }
                    .border(
                        width = dimens.x_1_dp,
                        color = borderColor
                            ?: if (isFocused) customColors.border else Color.Transparent,
                        shape = RoundedCornerShape(dimens.x_4_dp)
                    )
                    .then(modifier)
                    .semantics { contentDescription = semanticName },
                enabled = enabled,
                value = value,
                onValueChange = {
                    if(it.length <= maxChar){
                        errorMsg = Constants.EMPTY_STRING
                        onValueChange(CommonUtilities.trimStartSpaces(it))
                    }
                },
                maxLines = maxLines,
                singleLine = singleLine,
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
                isError = errorMsg.isNotEmpty(),
                colors = textFieldColors,
                label = {
                    if(labelAsteriskRequired){
                        LabelWithAsterisk(
                            labelString = labelString,
                            labelId = labelResource,
                            value = value,
                            labelTextColor = labelTextColor,
                            isFocused = isFocused
                        )
                    }else{
                        Text(
                            labelString.ifEmpty { stringResource(id = labelResource) },
                            style = LocalTextStyle.current.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = if (value.isNotEmpty() || isFocused) NewsAppTheme.fontSizes.x_0_80 else NewsAppTheme.fontSizes.x_1_2,
                                color = labelTextColor
                                    ?: if ((value.isEmpty() && !isFocused) || useDisabledColorsOnly) {
                                        Color.Black
                                    } else {
                                        MaterialTheme.colorScheme.primary
                                    }
                            )
                        )
                    }
                }

            )
        }
    }
}

@Composable
fun HeadingText(
    inputText: String,
    modifier: Modifier = Modifier,
    textColor: Color
){
    Text(
        text = inputText,
        style = LocalTextStyle.current.copy(
            fontWeight = FontWeight.Bold,
            fontSize = NewsAppTheme.fontSizes.x_42_0,
        ),
        color = textColor,
        modifier = modifier
    )
}
