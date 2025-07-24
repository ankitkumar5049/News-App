package com.practise.newsapp.presentation.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.ui.theme.NewsAppTheme

@Composable
fun ProfileImageView(
    modifier: Modifier,
    painter: Painter,
    contentDescription: String,
    onIconCLick: () -> Unit = {},
    onImageClick: () -> Unit = {},
){

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ){
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimen_mdpi.x_18_0)
                .clip(CircleShape)
                .clickable {
                    onImageClick()
                }
        )

        Icon(
            Icons.Filled.Camera,
            tint = NewsAppTheme.customColors.primary,
            contentDescription = CommonContentDescription.TOGGLE_PASSWORD_VISIBILITY,
            modifier = Modifier
                .requiredSize(dimen_mdpi.x_7_0)
                .padding(dimen_mdpi.x_1_25)
                .clickable {
                    onIconCLick()
                },
        )
    }

}