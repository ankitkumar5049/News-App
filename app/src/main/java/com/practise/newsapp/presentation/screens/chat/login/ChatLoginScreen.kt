package com.practise.newsapp.presentation.screens.chat.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.common.utils.LocalStrings
import com.practise.newsapp.common.utils.isValidUserName
import com.practise.newsapp.presentation.animations.SlideInVertically
import com.practise.newsapp.presentation.uiComponents.CommonButton
import com.practise.newsapp.presentation.uiComponents.CommonDatePickerField
import com.practise.newsapp.presentation.uiComponents.CommonDropdownField
import com.practise.newsapp.presentation.uiComponents.CommonTextField
import com.practise.newsapp.presentation.uiComponents.CommonTextInputFields
import com.practise.newsapp.presentation.uiComponents.HeadingText
import com.practise.newsapp.presentation.uiComponents.SubHeadingText
import com.practise.newsapp.ui.theme.NewsAppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ChatLoginScreen(
    viewModel: ChatLoginViewModel,
    navigate: (String, Boolean, String?, Boolean) -> Unit
){

    var visible by remember { mutableStateOf(false) }
    val genderOptions = listOf(
        "Male",
        "Female",
        "Other"
    )

    LaunchedEffect(Unit) {
        visible = true
    }

    Box {
        Scaffold { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(NewsAppTheme.customColors.background)
                    .padding(innerPadding)
                    .padding(
                        start = dimen_mdpi.x_2_0,
                        end = dimen_mdpi.x_2_0,
                        top = dimen_mdpi.x_2_0
                    )
            ) {

                SlideInVertically(
                    visible = visible,
                    fromTop = true,
                    enterDuration = 500,
                    exitDuration = 400,
                    initialOffsetY = 60,
                    targetScale = 1.1f,
                ) {
                    HeadingText(
                        inputText = CommonString.HELLO,
                        modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                        textColor = NewsAppTheme.customColors.primary,
                    )

                }

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))
                SubHeadingText(
                    inputText = LocalStrings.LETS_MAKE_NEW_FRIENDS
                )
                Spacer(modifier = Modifier.height(dimen_mdpi.x_6_0))

                CommonTextInputFields(
                    value = viewModel.state.username,
                    onValueChange = {
                        viewModel.state = viewModel.state.copy(
                            username = it
                        )
                    },
                    labelString = CommonString.USERNAME,
                    semanticName = CommonString.USERNAME,
                    maxChar = 20,
                    labelAsteriskRequired = true,
                    borderColor = if(viewModel.state.username.isNotEmpty() && !isValidUserName(viewModel.state.username)) Color.Red else NewsAppTheme.customColors.border,
                )
                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonDropdownField(
                    selectedValue = viewModel.state.gender,
                    onValueSelected = {
                        viewModel.state = viewModel.state.copy(
                            gender = it
                        )
                    },
                    options = genderOptions,
                    labelString = LocalStrings.GENDER,
                    semanticName = LocalStrings.GENDER,
                    borderColor = NewsAppTheme.customColors.border,
                    labelAsteriskRequired = true,

                )
                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonDatePickerField(
                    selectedDate = viewModel.state.dob,
                    labelString = LocalStrings.DATE_OF_BIRTH,
                    labelAsteriskRequired = true,
                    semanticName = LocalStrings.DATE_OF_BIRTH,
                    onDateSelected = {
                        viewModel.state = viewModel.state.copy(
                            dob = it
                        )
                    },
                    dateFormatter = { millis ->
                        SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                            .format(Date(millis))
                    },
                )
                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))


                Row(
                    modifier = Modifier,
//                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(
                        checked = viewModel.state.termsAndCondition,
                        onCheckedChange = {
                            viewModel.state = viewModel.state.copy(
                                termsAndCondition = !viewModel.state.termsAndCondition
                            )
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = NewsAppTheme.customColors.primary,
                            uncheckedColor = NewsAppTheme.customColors.primary,
                            checkmarkColor = Color.White
                        )

                    )

                    CommonTextField(
                        inputText = LocalStrings.TERMS_AND_CONDITIONS,
                        modifier = Modifier,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                CommonButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = LocalStrings.LETS_CHAT,
                    enabled = viewModel.isFilled(),
                    buttonModifier = Modifier
                        .padding(bottom = dimen_mdpi.x_32_dp),
                    onClick = {

                    }
                )



            }
        }
    }
}