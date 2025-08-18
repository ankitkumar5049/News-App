package com.practise.newsapp.presentation.screens.register.enterOTP

import android.os.CountDownTimer
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.navigation.NavigationItem
import com.practise.newsapp.presentation.uiComponents.CommonButton
import com.practise.newsapp.presentation.uiComponents.CommonTextField
import com.practise.newsapp.presentation.uiComponents.HeadingText
import com.practise.newsapp.presentation.uiComponents.NewsTopBar
import com.practise.newsapp.presentation.uiComponents.OtpInputField
import com.practise.newsapp.presentation.uiComponents.SubHeadingText
import com.practise.newsapp.ui.theme.NewsAppTheme
import kotlin.reflect.KFunction4

@Composable
fun OtpScreen(
    navigate: KFunction4<String, Boolean, String?, Boolean, Unit>,
    viewmodel: OtpVerificationViewModel
) {

    val otpLength = 4
    val otpValues = remember { mutableStateListOf("", "", "", "") }
    var tickTime = remember { mutableIntStateOf(0) }
    val focusRequesters = List(otpLength) { remember { FocusRequester() } }
    val showResendButton = remember { mutableStateOf(false) }

    val timer = object: CountDownTimer(60000, 1000) {
        override fun onTick(p0: Long) {
            tickTime.intValue = (p0/1000).toInt()
        }

        override fun onFinish() {
            showResendButton.value = true
        }

    }

    LaunchedEffect(Unit) {
        timer.start()
    }

    Box {
        Scaffold(
            modifier = Modifier
                .background(NewsAppTheme.customColors.topBar)
                .systemBarsPadding(),
            topBar = { NewsTopBar() }
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
                HeadingText(
                    inputText = CommonString.OTP_VERIFICATION,
                    modifier = Modifier,
                    textColor = NewsAppTheme.customColors.textPrimary,
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_2_0))

                SubHeadingText(
                    inputText = CommonString.ENTER_THE_OTP
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_2_0))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimen_mdpi.x_1_25, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    repeat(otpLength) { index ->
                        OtpInputField(
                            modifier = Modifier
                                .size(width = dimen_mdpi.x_8_0, height = dimen_mdpi.x_8_0)
                                .focusRequester(focusRequesters[index]),
                            value = otpValues[index],
                            onValueChange = { newValue ->
                                if (newValue.length <= 1) {
                                    val isDelete = newValue.isEmpty() && otpValues[index].isNotEmpty()
                                    otpValues[index] = newValue
                                    if (newValue.isNotEmpty() && index < otpLength - 1) {
                                        focusRequesters[index + 1].requestFocus()
                                    } else if (isDelete && index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }
                            },
                            onFocusedChange = { focusState ->
                                if (!focusState.isFocused && otpValues[index].isEmpty()) {
                                    otpValues[index] = ""
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimen_mdpi.x_2_0))

                SubHeadingText(
                    inputText = "${CommonString.RESEND_OTP_IN} ${tickTime.intValue}s"
                )

                if(showResendButton.value) {
                    CommonTextField(
                        inputText = "Resend",
                        isLink = true,
                        clickable = true,
                        modifier = Modifier,
                        onClick = {
//                        timer.start()
//                        showResendButton.value = false
                        }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                CommonButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = CommonString.VERIFY,
                    buttonModifier = Modifier
                        .padding(bottom = dimen_mdpi.x_32_dp),
                    onClick = {
                        navigate(
                            NavigationItem.ResetPassword.route,
                            true,
                            null,
                            true
                        )
                    }
                )
            }
        }
    }
}