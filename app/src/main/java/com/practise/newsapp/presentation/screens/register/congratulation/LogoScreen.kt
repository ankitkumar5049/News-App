package com.practise.newsapp.presentation.screens.register.congratulation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.navigation.NavigationItem
import com.practise.newsapp.presentation.animations.ScaleInTopLeft
import com.practise.newsapp.presentation.uiComponents.CommonButton
import com.practise.newsapp.presentation.uiComponents.HeadingText
import com.practise.newsapp.presentation.uiComponents.LargeHeadingText
import com.practise.newsapp.ui.theme.NewsAppTheme
import kotlin.reflect.KFunction4

@Composable
fun LogoScreen(navigate: KFunction4<String, Boolean, String?, Boolean, Unit>) {

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Box {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .align(Alignment.Center)
                    .background(NewsAppTheme.customColors.background)
                    .padding(
                        start = dimen_mdpi.x_2_0,
                        end = dimen_mdpi.x_2_0,
                        bottom = dimen_mdpi.x_2_0
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LargeHeadingText(
                    inputText = CommonString.APP_NAME,
                    modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                    textColor = NewsAppTheme.customColors.primary,
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                ScaleInTopLeft(
                    visible = visible
                ) {
                    HeadingText(
                        inputText = CommonString.CONGRATULATIONS,
                        modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                        textColor = NewsAppTheme.customColors.textPrimary,
                    )
                }


                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = CommonString.HOMEPAGE,
                    buttonModifier = Modifier
                        .padding(bottom = dimen_mdpi.x_32_dp),
                    onClick = {
                        navigate(
                            NavigationItem.Home.route,
                            true,
                            NavigationItem.LogoScreen.route,
                            true
                        )
                    }
                )

            }
        }
    }
}