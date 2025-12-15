package com.practise.newsapp.presentation.screens.register.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.common.utils.LocalStrings
import com.practise.newsapp.navigation.NavigationItem
import com.practise.newsapp.presentation.animations.SlideInHorizontally
import com.practise.newsapp.presentation.uiComponents.CommonButton
import com.practise.newsapp.presentation.uiComponents.CommonTextField
import com.practise.newsapp.presentation.uiComponents.CommonTextInputFields
import com.practise.newsapp.presentation.uiComponents.HeadingText
import com.practise.newsapp.presentation.uiComponents.SubHeadingText
import com.practise.newsapp.ui.theme.NewsAppTheme
import com.practise.newsapp.ui.theme.NewsAppTheme.customColors
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigate: (String, Boolean, String?, Boolean) -> Unit,
) {
    var showPassword by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        visible = true
    }

    LaunchedEffect(viewModel.effects.receiveAsFlow()) {
        viewModel.effects.receiveAsFlow().onEach { effect ->
            when (effect) {
                LoginContract.Effect.LaunchHomeScreen -> {
                    navigate(
                        NavigationItem.Home.route,
                        true,
                        NavigationItem.Signup.route,
                        true
                    )
                }
                LoginContract.Effect.LaunchForgotPasswordScreen -> {

                }
                LoginContract.Effect.LaunchSignUpScreen -> {
                    navigate(
                        NavigationItem.Signup.route,
                        true,
                        NavigationItem.Login.route,
                        true
                    )
                }
            }
        }
    }

    Box {
        Scaffold { innerPadding ->
            Column (
                Modifier
                    .fillMaxSize()
                    .background(NewsAppTheme.customColors.background)
                    .padding(innerPadding)
                    .padding(start = dimen_mdpi.x_2_0, end = dimen_mdpi.x_2_0, top = dimen_mdpi.x_2_0)
            ){


                SlideInHorizontally(
                    visible = visible,
                    fromLeft = true
                ) {
                    HeadingText(
                        CommonString.HELLO,
                        textColor = NewsAppTheme.customColors.textPrimary,
                        modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                    )
                }

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                SlideInHorizontally(
                    visible = visible,
                    fromLeft = false
                ) {
                    HeadingText(
                        CommonString.AGAIN,
                        textColor = NewsAppTheme.customColors.primary,
                        modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                    )
                }



                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                SubHeadingText(
                    inputText = CommonString.WELCOME_BACK
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
                    maxLines = 1,
                    labelAsteriskRequired = true,
                    borderColor = if(viewModel.state.username.length in 1..3) Color.Red else NewsAppTheme.customColors.border,
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

                Spacer(modifier = Modifier.height(dimen_mdpi.x_0_75))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){

                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start) {
                        Checkbox(
                            checked = viewModel.state.rememberMe,
                            onCheckedChange = {
                                viewModel.state = viewModel.state.copy(
                                    rememberMe = !viewModel.state.rememberMe
                                )
                                              },
                            colors = CheckboxDefaults.colors(
                                checkedColor = NewsAppTheme.customColors.primary,
                                uncheckedColor = NewsAppTheme.customColors.primary,
                                checkmarkColor = Color.White
                            )

                        )

                        CommonTextField(
                            inputText = CommonString.REMEMBER_ME,
                            modifier = Modifier
                        )
                    }

                    CommonTextField(
                        modifier = Modifier.clickable {
                            navigate(
                                NavigationItem.ForgotPassword.route,
                                true,
                                null,
                                true
                            )
                        },
                        inputText = CommonString.FORGOT_THE_PASSWORD,
                        isLink = true,
                    )

                }
                CommonButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = CommonString.LOGIN,
                    enabled = viewModel.isFilled(),
                    buttonModifier = Modifier
                        .padding(bottom = dimen_mdpi.x_32_dp),
                    onClick = {
                        viewModel.login(
                            email = viewModel.state.username,
                            password = viewModel.state.password,
                            context = context,
                            onResult = {
                                navigate(
                                    NavigationItem.Home.route,
                                    true,
                                    NavigationItem.Login.route,
                                    true
                                )
                            }
                        )
                    }
                )

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    CommonTextField(
                        inputText = CommonString.DONT_HAVE_AN_ACCOUNT,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.padding(dimen_mdpi.x_0_75))

                    CommonTextField(
                        inputText = CommonString.SIGN_UP,
                        isLink = true,
                        modifier = Modifier.clickable {
                            navigate(
                                NavigationItem.Signup.route,
                                true,
                                NavigationItem.Login.route,
                                true
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(dimen_mdpi.x_0_75))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CommonTextField(
                        inputText = LocalStrings.LETS_CHAT,
                        isLink = true,
                        modifier = Modifier.clickable {
                            navigate(
                                NavigationItem.ChatLogin.route,
                                false,
                                NavigationItem.Login.route,
                                true
                            )
                        }
                    )
                }

            }
        }
    }
}