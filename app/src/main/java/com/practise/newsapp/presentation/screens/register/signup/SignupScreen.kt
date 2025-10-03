package com.practise.newsapp.presentation.screens.register.signup

import android.util.Log
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
import androidx.lifecycle.viewModelScope
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.utils.CommonContentDescription
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.navigation.NavigationItem
import com.practise.newsapp.presentation.animations.SlideInVertically
import com.practise.newsapp.presentation.uiComponents.CommonButton
import com.practise.newsapp.presentation.uiComponents.CommonTextField
import com.practise.newsapp.presentation.uiComponents.CommonTextInputFields
import com.practise.newsapp.presentation.uiComponents.HeadingText
import com.practise.newsapp.presentation.uiComponents.LogoPulseLoader
import com.practise.newsapp.presentation.uiComponents.SubHeadingText
import com.practise.newsapp.presentation.uiComponents.WaitScreen
import com.practise.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(
    viewModel: SignupViewModel,
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
                SignupContract.Effect.LaunchHomeScreen -> {
                    navigate(
                        NavigationItem.Home.route,
                        true,
                        NavigationItem.Signup.route,
                        true
                    )
                }
            }
        }
    }

    Box {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
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
                    inputText = CommonString.SIGN_UP_TO_GET_STARTED
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
                    borderColor = if(viewModel.state.username.length in 1..3) Color.Red else NewsAppTheme.customColors.border,
                )
                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = viewModel.state.email,
                    onValueChange = {
                        viewModel.state = viewModel.state.copy(
                            email = it
                        )
                    },
                    labelString = CommonString.EMAIL,
                    semanticName = CommonString.EMAIL,
                    maxChar = 30,
                    labelAsteriskRequired = true,
                    borderColor = if(viewModel.state.email.length in 1..5) Color.Red else NewsAppTheme.customColors.border,
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

                }
                CommonButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = CommonString.SIGN_UP,
                    buttonModifier = Modifier
                        .padding(bottom = dimen_mdpi.x_32_dp),
                    onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.createNewUser(
                                viewModel.state.username,
                                viewModel.state.email,
                                viewModel.state.password,
                                onResult = {
                                    viewModel.viewModelScope.launch {
                                        viewModel.effects.send(SignupContract.Effect.LaunchHomeScreen)
                                    }
                                    navigate(
                                        NavigationItem.Home.route,
                                        true,
                                        NavigationItem.Signup.route,
                                        true
                                    )
                                }
                            )
                        }

                    }
                )

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    CommonTextField(
                        inputText = CommonString.ALREADY_HAVE_AN_ACCOUNT,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.padding(dimen_mdpi.x_0_75))

                    CommonTextField(
                        inputText = CommonString.LOGIN,
                        isLink = true,
                        modifier = Modifier.clickable {
                            navigate(
                                NavigationItem.Login.route,
                                true,
                                NavigationItem.Signup.route,
                                true
                            )
                        }

                    )
                }
            }

            if(viewModel.apiState.isLoading){
                WaitScreen()
            }
        }
    }
}
