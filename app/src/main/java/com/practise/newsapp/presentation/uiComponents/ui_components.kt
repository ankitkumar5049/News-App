package com.practise.newsapp.presentation.uiComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.ui.theme.NewsAppTheme.fontSizes

@Composable
fun LabelWithAsterisk(
    labelString: String = Constants.EMPTY_STRING,
    labelId: Int,
    fontSize: TextUnit = fontSizes.x_1_2,
    value: String,
    labelTextColor: Color? = null,
    isFocused: Boolean
){
    Row {
        Text(
            text = labelString.ifEmpty { stringResource(id = labelId) },
            style = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
//                color = labelTextColor ?: if (value.isEmpty() && !isFocused) {
//                    Color.Black
//                } else {
//                    MaterialTheme.
//                }
            ),
        )
        Text(
            text = Constants.ASTERISK,
            style = LocalTextStyle.current.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = fontSize,
//                color = labelTextColor ?: if (value.isEmpty() && !isFocused) {
//                    OsloGray
//                } else {
//                    MaterialTheme.colors.primary
//                }
            )
        )
    }

}