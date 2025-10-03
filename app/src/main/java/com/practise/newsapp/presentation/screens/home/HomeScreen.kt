package com.practise.newsapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.domain.Articles
import com.practise.newsapp.navigation.NavigationItem
import com.practise.newsapp.presentation.uiComponents.AppDrawer
import com.practise.newsapp.presentation.uiComponents.HeadlineCard
import com.practise.newsapp.presentation.uiComponents.LogoPulseLoader
import com.practise.newsapp.presentation.uiComponents.NewsCard
import com.practise.newsapp.presentation.uiComponents.NewsTopBar
import com.practise.newsapp.presentation.uiComponents.SearchBarInputField
import com.practise.newsapp.presentation.uiComponents.ShowNewsDescBottomSheet
import com.practise.newsapp.presentation.uiComponents.WaitScreen
import com.practise.newsapp.ui.theme.BluePrimary
import com.practise.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewmodel: HomeViewModel,
    navigate: (String, Boolean, String?, Boolean) -> Unit,
){

    val headlines = arrayOf("Trending", "Business", "Entertainment", "Cricket", "Politics", "General Health", "Science", "Sports", "Technology")
    var selectedHeadline by remember { mutableStateOf<String?>("Trending") }
    var selectedHeadlineIndex by remember { mutableStateOf(0) }
    var selectedArticle by remember { mutableStateOf<Articles?>(null) }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { headlines.size })

    LaunchedEffect(Unit) {
        viewmodel.getNews(
            query = selectedHeadline ?: "Trending",
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                onItemClick = { item ->
                    when (item) {
                        "item1" -> { /* Navigate or action */ }
                        "item2" -> { /* Navigate or action */ }
                        "settings" -> { /* Navigate to settings */ }
                        "help" -> { /* Navigate to help */ }
                    }
                    scope.launch { drawerState.close() } // close after selection
                }
            )
        },
        drawerState = drawerState
    ) {
            Box {
                val inputText = remember { mutableStateOf("") }
                Scaffold(
                    modifier = Modifier
                        .background(NewsAppTheme.customColors.topBar)
                        .systemBarsPadding(),
                    topBar = {
                        NewsTopBar(
                            showHamburgerIcon = true,
                            showBackButton = false,
                            showAppLogo = true,
                            showNotificationIcon = true,
                            onHamburgerMenuClick = {
                                scope.launch {
                                    if (drawerState.isClosed) {
                                        drawerState.open()
                                    } else {
                                        drawerState.close()
                                    }
                                }
                            },
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

                        ScrollableTabRow(
                            modifier = Modifier.padding(
                                bottom = dimen_mdpi.x_2_0
                            ),
                            selectedTabIndex = pagerState.currentPage,
                            edgePadding = 0.dp
                        ) {
                            headlines.forEachIndexed { index, title ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        scope.launch { pagerState.animateScrollToPage(index) }
                                    },
                                    text = {
                                        Text(
                                            text = title,
                                            color = if (pagerState.currentPage == index)
                                                BluePrimary
                                            else
                                                NewsAppTheme.customColors.textPrimary
                                        )
                                    }
                                )
                            }
                        }

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->

                            val headline = headlines[page]
                            LaunchedEffect(page) {
                                viewmodel.getNews(headline)
                            }

                            if (viewmodel.apiState.isLoading) {
                                WaitScreen()
                            } else {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.spacedBy(dimen_mdpi.x_0_75)
                                ) {
                                    items(
                                        viewmodel.state.articles,
                                        key = { it.hashCode() }) { article ->
                                        NewsCard(
                                            headline = article.title,
                                            source = article.source?.name,
                                            imageUrl = article.urlToImage,
                                            onClick = { selectedArticle = article }
                                        )
                                    }
                                }
                            }
                        }

                        // Bottom sheet
                        selectedArticle?.let {
                            ShowNewsDescBottomSheet(it) { selectedArticle = null }
                        }
                    }
                }
            }
        }
}