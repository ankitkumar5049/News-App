package com.practise.newsapp.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.practise.newsapp.R
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.presentation.uiComponents.CommonButton
import com.practise.newsapp.presentation.uiComponents.CommonTextInputFields
import com.practise.newsapp.presentation.uiComponents.NewsTopBar
import com.practise.newsapp.presentation.uiComponents.ProfileImageView
import com.practise.newsapp.ui.theme.NewsAppTheme

@Composable
fun ProfileScreen(){

    Box {
        Scaffold(
            modifier = Modifier
                .background(NewsAppTheme.customColors.topBar)
                .systemBarsPadding(),
            topBar = { NewsTopBar(
                title = CommonString.FILL_YOUR_PROFILE
            ) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(NewsAppTheme.customColors.background)
                    .padding(innerPadding)
                    .padding(
                        start = dimen_mdpi.x_2_0,
                        end = dimen_mdpi.x_2_0,
                        bottom = dimen_mdpi.x_2_0,
                        top = dimen_mdpi.x_2_0
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ProfileImageView(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = CommonContentDescription.PROFILE_IMAGE
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = "user223",
                    labelString = CommonString.USERNAME,
                    semanticName = CommonString.USERNAME,
                    readOnly = true,
                    maxChar = 20,
                    maxLines = 1,
                    labelAsteriskRequired = false,
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = "Ankit Kumar",
                    labelString = CommonString.FULL_NAME,
                    semanticName = CommonString.FULL_NAME,
                    maxChar = 20,
                    maxLines = 1,
                    labelAsteriskRequired = false,
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = "ankit@gmail.com",
                    labelString = CommonString.EMAIL_ADDRESS,
                    semanticName = CommonString.EMAIL_ADDRESS,
                    maxChar = 20,
                    maxLines = 1,
                    labelAsteriskRequired = true,
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = "+91 7903888878",
                    labelString = CommonString.PHONE_NUMBER,
                    semanticName = CommonString.PHONE_NUMBER,
                    maxChar = 20,
                    maxLines = 1,
                    labelAsteriskRequired = true,
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.weight(1f))

                CommonButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = CommonString.NEXT,
                    buttonModifier = Modifier
                        .padding(bottom = dimen_mdpi.x_32_dp),
                    onClick = {

                    }
                )

            }
        }
    }
}