package com.practise.newsapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.navigation.NavigationItem
import com.practise.newsapp.presentation.uiComponents.HeadlineCard
import com.practise.newsapp.presentation.uiComponents.NewsCard
import com.practise.newsapp.presentation.uiComponents.NewsTopBar
import com.practise.newsapp.presentation.uiComponents.SearchBarInputField
import com.practise.newsapp.ui.theme.NewsAppTheme

@Composable
fun HomeScreen(
    viewmodel: HomeViewModel,
    navigate: (String, Boolean, String?, Boolean) -> Unit,
){

    val headlines = arrayOf("Trending", "Business", "Entertainment", "Cricket", "Politics", "General Health", "Science", "Sports", "Technology")
    var selectedHeadline by remember { mutableStateOf<String?>("Trending") }
    LaunchedEffect(Unit) {
        viewmodel.getNews()
    }

    Box {
        val inputText  = remember { mutableStateOf("") }
        Scaffold(
            modifier = Modifier
                .background(NewsAppTheme.customColors.topBar)
                .systemBarsPadding(),
            topBar = {
                NewsTopBar(
                    showBackButton = false,
                    showAppLogo = true,
                    showNotificationIcon = true,
                    onNotificationMenuClick = {
                        navigate(
                            NavigationItem.Notification.route,
                            true,
                            null,
                            true
                        )
                    }

                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(NewsAppTheme.customColors.background)
                    .padding(
                        start = dimen_mdpi.x_2_0,
                        end = dimen_mdpi.x_2_0,
                        bottom = dimen_mdpi.x_2_0
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SearchBarInputField(
                    value = inputText.value,
                    onValueChange = {},
                    trailingIcon = {
                        Icon(
                            Icons.Outlined.FilterAlt,
                            contentDescription = CommonContentDescription.TOGGLE_PASSWORD_VISIBILITY,
                            modifier = Modifier
                                .requiredSize(dimen_mdpi.x_6_0)
                                .padding(dimen_mdpi.x_1_25)
                        )
                    },
                )

                Row(
                    modifier = Modifier
                        .padding(top = dimen_mdpi.x_1_25, bottom = dimen_mdpi.x_1_25)
                        .fillMaxWidth()
                        .background(NewsAppTheme.customColors.background)
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimen_mdpi.x_1_dp)
                    ) {
                        items(
                            items = headlines,
                            key = {it.hashCode()}
                        ){ headline ->
                            HeadlineCard(
                                headLine = headline,
                                isSelected = headline == selectedHeadline,
                                onClick = {selectedHeadline = headline},
                            )
                        }
                    }
                }


                if(viewmodel.apiState.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        color = NewsAppTheme.customColors.primary
                    )
                }
                else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(dimen_mdpi.x_0_75)
                    ) {
                        items(
                            items = viewmodel.state.articles,
                            key = { it.hashCode() }
                        ) { article ->
                            NewsCard(
                                headline = article.title,
                                imageUrl = article.urlToImage
                            )
                        }
                    }
                }
            }
        }
    }
}