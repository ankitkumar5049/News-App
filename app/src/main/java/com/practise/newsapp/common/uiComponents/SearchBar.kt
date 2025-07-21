package com.practise.newsapp.common.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.ui.theme.BluePrimary
import com.practise.newsapp.ui.theme.NewsAppTheme
import com.practise.newsapp.ui.theme.NewsAppTheme.customColors
import com.practise.newsapp.ui.theme.NewsAppTheme.dimens
import com.practise.newsapp.ui.theme.OsloGray

@Composable
fun SearchBarInputField(
    placeholderText: String = CommonString.SEARCH,
    trailingIcon: @Composable (() -> Unit)? = null,
    value: String = Constants.EMPTY_STRING,
    onValueChange: (newValue: String) -> Unit,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    textFieldColors: TextFieldColors =
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

){
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {

            }
            .clip(RoundedCornerShape(dimens.x_4_dp))
            .background(customColors.textFieldBackground)
            .border(
                width = dimens.x_1_dp,
                color = customColors.border,
                shape = RoundedCornerShape(dimens.x_4_dp)
            ),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = NewsAppTheme.fontSizes.x_1_25,
            color = customColors.textPrimary
        ),
        maxLines = maxLines,
        singleLine = singleLine,
        colors = textFieldColors,
        placeholder = {
            Text(
                text = placeholderText,
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = NewsAppTheme.fontSizes.x_1_25,
                ),
                color = customColors.textPrimary
            )
        },
        trailingIcon = trailingIcon,
    )
}