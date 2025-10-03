package com.practise.newsapp.presentation.uiComponents

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.practise.newsapp.R
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.common.utils.Constants
import com.practise.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@Composable
fun NewsTopBar(
    showBackButton: Boolean = true,
    showAppLogo: Boolean = false,
    onBackPress: (() -> Unit)? = null,
    title: String = Constants.EMPTY_STRING,
    ellipsis: Boolean = false,
    showStatusBar: Boolean = true,
    showOptionMenuIcon: Boolean = false,
    showNotificationIcon: Boolean = false,
    onNotificationMenuClick: (() -> Unit)? = null,
    showNavigationBar: Boolean = true,
    showHamburgerIcon: Boolean = false,
    onHamburgerMenuClick: (() -> Unit)? = null,
    icons: (@Composable () -> Unit)? = null
){

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var showMenu by remember { mutableStateOf(false) }

//    val systemUiController: SystemUiController = rememberSystemUiController()
//    systemUiController.isStatusBarVisible = showStatusBar // Status bar
//    systemUiController.isNavigationBarVisible = showNavigationBar // Navigation bar

    Row(
        modifier = Modifier
            .height(NewsAppTheme.dimens.x_2_0)
            .background(NewsAppTheme.customColors.topBar)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = {
            onHamburgerMenuClick?.invoke()
        }) {
            Image(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "SideBar",
                colorFilter = ColorFilter.tint(color = NewsAppTheme.customColors.surface)
            )
        }

        if(showBackButton){
            IconButton(
                onClick = {
                    if (onBackPress != null) {
                        onBackPress.invoke()
                    } else {
                        onBackPressedDispatcher?.onBackPressed()
                    }
                }
            ) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    colorFilter = ColorFilter.tint(color = NewsAppTheme.customColors.textPrimary),
                    contentDescription = CommonContentDescription.BACK_BUTTON
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(start = if(showBackButton || showHamburgerIcon) NewsAppTheme.dimens.x_0_dp else NewsAppTheme.dimens.x_16_dp)
                .weight(Constants.FULL_OPACITY),
            horizontalArrangement = Arrangement.Start //if(showAppLogo) Arrangement.Center else Arrangement.Start
        ) {
            if(showAppLogo){
                Image(
                    modifier = Modifier.padding(vertical = NewsAppTheme.dimens.x_4_dp),
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = Constants.EMPTY_STRING
                )
            }
            else{
                SubtitleText(
                    text = title,
                    ellipsis = ellipsis,
                    modifier = Modifier.padding(end = NewsAppTheme.dimens.x_16_dp),
                    color = NewsAppTheme.customColors.textPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        icons?.invoke()

        if(showNotificationIcon){
            IconButton(onClick = {
                onNotificationMenuClick?.invoke()
            }) {
                Image(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notification",
                    colorFilter = ColorFilter.tint(color = NewsAppTheme.customColors.surface)
                )
            }
        }

        if (showOptionMenuIcon) {
            IconButton(
                onClick = {
//                showMenu = !showMenu
                }
            ) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.Help,
                    contentDescription = "Help",
                    colorFilter = ColorFilter.tint(color = NewsAppTheme.customColors.surface)
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {

            }
        }
    }

}