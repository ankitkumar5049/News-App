package com.practise.newsapp.screens.register.forgotPassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.uiComponents.CommonButton
import com.practise.newsapp.common.uiComponents.CommonTextInputFields
import com.practise.newsapp.common.uiComponents.HeadingText
import com.practise.newsapp.common.uiComponents.SubHeadingText
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.navigation.NavigationItem
import com.practise.newsapp.ui.theme.NewsAppTheme
import kotlin.reflect.KFunction4

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel,
    navigate: KFunction4<String, Boolean, String?, Boolean, Unit>
) {

    Box {
        Scaffold{ innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(start = dimen_mdpi.x_2_0, end = dimen_mdpi.x_2_0, top = dimen_mdpi.x_2_0, bottom = dimen_mdpi.x_2_0)
            ) {
                HeadingText(
                    inputText = CommonString.FORGOT_PASSWORD,
                    modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                    textColor = NewsAppTheme.customColors.textPrimary,
                )

                SubHeadingText(
                    inputText = CommonString.FORGOT_PASSWORD_DESCRIPTION
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = viewModel.state.emailOrPhone,
                    onValueChange = {
                        viewModel.state = viewModel.state.copy(
                            emailOrPhone = it
                        )
                    },
                    labelString = CommonString.EMAIL_OR_PHONE_NUMBER,
                    semanticName = CommonString.EMAIL_OR_PHONE_NUMBER,
                    maxChar = 20,
                    maxLines = 1,
                    borderColor = if(viewModel.state.emailOrPhone.length in 1..3) Color.Red else NewsAppTheme.customColors.border,
                )

                Spacer(modifier = Modifier.weight(1f))

                CommonButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = CommonString.SUBMIT,
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