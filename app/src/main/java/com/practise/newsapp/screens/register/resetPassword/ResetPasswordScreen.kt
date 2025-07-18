package com.practise.newsapp.screens.register.resetPassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.uiComponents.CommonButton
import com.practise.newsapp.common.uiComponents.CommonTextInputFields
import com.practise.newsapp.common.uiComponents.HeadingText
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.ui.theme.NewsAppTheme
import kotlin.reflect.KFunction4

@Composable
fun ResetPasswordScreen(
    viewModel: ResetPasswordViewModel,
    navigate: KFunction4<String, Boolean, String?, Boolean, Unit>
) {

    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    Box {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = dimen_mdpi.x_2_0, end = dimen_mdpi.x_2_0, top = dimen_mdpi.x_2_0, bottom = dimen_mdpi.x_2_0)
            ) {
                HeadingText(
                    inputText = CommonString.RESET_PASSWORD,
                    modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                    textColor = NewsAppTheme.customColors.textPrimary,
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = viewModel.state.password,
                    onValueChange = {
                        viewModel.state = viewModel.state.copy(
                            password = it
                        )
                    },
                    labelString = CommonString.PASSWORD,
                    semanticName = CommonString.PASSWORD,
                    maxChar = 20,
                    labelAsteriskRequired = true,
                    trailingIcon = {
                        Icon(
                            if (showPassword) {
                                Icons.Filled.Visibility

                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = CommonContentDescription.TOGGLE_PASSWORD_VISIBILITY,
                            modifier = Modifier
                                .requiredSize(48.dp)
                                .padding(16.dp)
                                .clickable { showPassword = !showPassword }
                        )
                    },
                    enforceVisualTransformation = true,
                    borderColor = if(viewModel.state.password.length in 1..3) Color.Red else NewsAppTheme.customColors.border,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = viewModel.state.password,
                    onValueChange = {
                        viewModel.state = viewModel.state.copy(
                            password = it
                        )
                    },
                    labelString = CommonString.PASSWORD,
                    semanticName = CommonString.PASSWORD,
                    maxChar = 20,
                    labelAsteriskRequired = true,
                    trailingIcon = {
                        Icon(
                            if (showConfirmPassword) {
                                Icons.Filled.Visibility

                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = CommonContentDescription.TOGGLE_PASSWORD_VISIBILITY,
                            modifier = Modifier
                                .requiredSize(48.dp)
                                .padding(16.dp)
                                .clickable { showConfirmPassword = !showConfirmPassword }
                        )
                    },
                    enforceVisualTransformation = true,
                    borderColor = if(viewModel.state.password.length in 1..3) Color.Red else NewsAppTheme.customColors.border,
                    visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.weight(1f))

                CommonButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = CommonString.SUBMIT,
                    buttonModifier = Modifier
                        .padding(bottom = dimen_mdpi.x_32_dp),
                    onClick = {

                    }
                )
            }
        }
    }
}