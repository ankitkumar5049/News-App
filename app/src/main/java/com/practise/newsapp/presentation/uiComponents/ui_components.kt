package com.practise.newsapp.presentation.uiComponents

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.practise.newsapp.R
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.dimensions.dimen_xhdpi
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.domain.Articles
import com.practise.newsapp.ui.theme.BluePrimary
import com.practise.newsapp.ui.theme.NewsAppTheme
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
    source: String? = Constants.EMPTY_STRING,
    imageUrl: String? = Constants.EMPTY_STRING,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable {
                onClick()
            }
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

            Column(
                modifier = Modifier
                    .padding(start = dimen_xhdpi.x_0_75, end = dimen_xhdpi.x_0_75)
            ) {
                headline?.let {
                    SubHeadingText(
                        inputText = it,
                        textColor = NewsAppTheme.customColors.textPrimary,
                        maxLines = 2
                    )
                }

                source?.let {
                    SubHeadingText(
                        inputText = it,
                        fontWeight = FontWeight.SemiBold,
                        textColor = NewsAppTheme.customColors.textPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun HeadlineCard(
    headLine: String? = Constants.EMPTY_STRING,
    isSelected: Boolean = false,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clickable{
                onClick()
            }
            .padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = NewsAppTheme.customColors.background
        )
    ) {
        Column {
            SubHeadingText(
                inputText = headLine?: Constants.EMPTY_STRING,
                textColor = if (isSelected) NewsAppTheme.customColors.primary else NewsAppTheme.customColors.textPrimary,
                modifier = Modifier.background(
                    color = NewsAppTheme.customColors.background
                )
            )
        }

    }
}

@Composable
fun LogoBounceLoader() {
    val offsetY = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            offsetY.animateTo(10f, tween(300)) // drop
            offsetY.animateTo(0f, tween(300, easing = LinearEasing)) // bounce up
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Loading",
            modifier = Modifier
                .size(dimen_mdpi.x_10_75)
                .offset(y = offsetY.value.dp),
            tint = NewsAppTheme.customColors.primary
        )
    }
}


@Composable
fun LogoPulseLoader() {
    val scale = remember { Animatable(1f) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        while (true) {
            scale.animateTo(1.2f, animationSpec = tween(500)) // grow
            alpha.animateTo(0.7f, animationSpec = tween(500))
            scale.animateTo(1f, animationSpec = tween(500))   // shrink
            alpha.animateTo(1f, animationSpec = tween(500))
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Loading",
            modifier = Modifier
                .size(dimen_mdpi.x_10_75)
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    alpha = alpha.value
                ),
            tint = NewsAppTheme.customColors.primary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowNewsDescBottomSheet(
    articles: Articles,
    onDismiss: () -> Unit
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)


    ModalBottomSheet(
        sheetMaxWidth = screenWidth,
        sheetState = sheetState,
        modifier = Modifier,

        onDismissRequest = {
             onDismiss()
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(articles.urlToImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Fit
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

            Spacer(modifier = Modifier.height(dimen_mdpi.x_0_75))
            SubtitleText(
                text = articles.title.toString(),
                modifier = Modifier,
                color = NewsAppTheme.customColors.textPrimary
            )
            Spacer(modifier = Modifier.height(dimen_mdpi.x_0_75))
            SubHeadingText(
                inputText = articles.description.toString(),
                textColor = NewsAppTheme.customColors.textPrimary
            )
            Spacer(modifier = Modifier.height(dimen_mdpi.x_0_75))
            SubHeadingText(
                inputText = articles.source?.name.toString(),
                textColor = NewsAppTheme.customColors.textPrimary,
                fontWeight = FontWeight.SemiBold
            )

        }
    }
}

@Composable
fun AppDrawer(
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(12.dp))
            Text(
                "Welcome!",
                color = BluePrimary,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            HorizontalDivider()

            Text(
                "Profile Details",
                color = NewsAppTheme.customColors.textPrimary,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            NavigationDrawerItem(
                label = { Text("Edit Profile") },
                selected = false,
                onClick = { onItemClick("item1") }
            )
            NavigationDrawerItem(
                label = { Text("Edit Password") },
                selected = false,
                onClick = { onItemClick("item2") }
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                "Settings and Help",
                color = NewsAppTheme.customColors.textPrimary,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            NavigationDrawerItem(
                label = { Text("Settings") },
                selected = false,
                icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                onClick = { onItemClick("settings") }
            )
            NavigationDrawerItem(
                label = { Text("Help and feedback") },
                selected = false,
                icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null) },
                onClick = { onItemClick("help") }
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}


