package com.practise.newsapp.presentation.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.ui.theme.NewsAppTheme
import com.practise.newsapp.ui.theme.NewsAppTheme.dimens
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

@Composable
fun NewsCard(
    headline: String? = Constants.EMPTY_STRING,
    imageUrl: String? = Constants.EMPTY_STRING,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "News Image",
                modifier = Modifier
                    .size(width = 100.dp, height = 80.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                    is AsyncImagePainter.State.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.BrokenImage,
                                contentDescription = "Image not available",
                                tint = Color.White
                            )
                        }
                    }
                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

            Column {
                headline?.let {
                    SubHeadingText(
                        inputText = it,
                        textColor = NewsAppTheme.customColors.textPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun HeadlineCard(
    headLine: String? = Constants.EMPTY_STRING
){
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = NewsAppTheme.customColors.background
        )
    ) {
        Column {
            SubHeadingText(
                inputText = headLine?: Constants.EMPTY_STRING,
                textColor = NewsAppTheme.customColors.textPrimary,
                modifier = Modifier.background(
                    color = NewsAppTheme.customColors.background
                )
            )
        }

    }
}
