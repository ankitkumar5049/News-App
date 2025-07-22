package com.practise.newsapp.presentation.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.practise.newsapp.ui.theme.BluePrimary
import com.practise.newsapp.ui.theme.NewsAppTheme
import com.practise.newsapp.ui.theme.NewsAppTheme.customColors
import com.practise.newsapp.ui.theme.NewsAppTheme.dimens
import com.practise.newsapp.ui.theme.OsloGray

@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    value: String,
    maxChar: Int = 1,
    onValueChange: (newValue: String) -> Unit,
    onFocusedChange: ((FocusState) -> Unit)? = null,
    borderColor: Color = Color.Black,
    backgroundColor: Color = customColors.topBar,
    textFieldColors: TextFieldColors =
        TextFieldDefaults.colors(
            disabledTextColor = Color.Black,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            cursorColor = BluePrimary,
            errorCursorColor = BluePrimary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = OsloGray,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
){
    TextField(
        modifier = Modifier
            .clip(RoundedCornerShape(dimens.x_4_dp))
            .background(backgroundColor)
            .border(
                width = dimens.x_1_dp,
                color = borderColor,
                shape = RoundedCornerShape(dimens.x_4_dp)
            )
            .onFocusChanged {
                onFocusedChange?.invoke(it)
            }
            .then(modifier),
        value = value,
        onValueChange = {
            if (it.length <= maxChar) {
                onValueChange(it)
            }
        },
        maxLines = 1,
        singleLine = true,
        colors = textFieldColors,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        textStyle = LocalTextStyle.current.copy(
            fontWeight = FontWeight.Bold,
            fontSize = NewsAppTheme.fontSizes.x_2_25,
            textAlign = TextAlign.Center
        )
    )
}