package com.practise.newsapp.register.login

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.uiComponents.CommonButton
import com.practise.newsapp.common.uiComponents.CommonTextField
import com.practise.newsapp.common.uiComponents.CommonTextInputFields
import com.practise.newsapp.common.uiComponents.HeadingText
import com.practise.newsapp.common.uiComponents.SubHeadingText
import com.practise.newsapp.ui.theme.NewsAppTheme

@Composable
fun LoginScreen(){

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(true) }
    Box {
        Scaffold { innerPadding ->
            Column (Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp)
            ){


                HeadingText(
                    "Hello",
                    textColor = NewsAppTheme.customColors.textPrimary,
                    modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                HeadingText(
                    "Again!",
                    textColor = NewsAppTheme.customColors.primary,
                    modifier = Modifier.padding(top = dimen_mdpi.x_32_dp),
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                SubHeadingText(
                    inputText = "Welcome back you've \nbeen missed"
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_6_0))

                CommonTextInputFields(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    labelString = "Username",
                    semanticName = "Username",
                    maxChar = 20,
                    labelAsteriskRequired = true,
                    borderColor = if(username.length in 1..3) Color.Red else NewsAppTheme.customColors.border,
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    labelString = "Password",
                    semanticName = "Password",
                    maxChar = 20,
                    labelAsteriskRequired = true,
                    trailingIcon = {
                        Icon(
                            if (showPassword) {
                                Icons.Filled.Visibility

                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = "Toggle password visibility",
                            modifier = Modifier
                                .requiredSize(48.dp).padding(16.dp)
                                .clickable { showPassword = !showPassword }
                        )
                    },
                    enforceVisualTransformation = true,
                    borderColor = if(password.length in 1..3) Color.Red else NewsAppTheme.customColors.border,
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
                            checked = checked,
                            onCheckedChange = { checked = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = NewsAppTheme.customColors.primary,
                                uncheckedColor = NewsAppTheme.customColors.primary,
                                checkmarkColor = Color.White
                            )

                        )

                        CommonTextField(
                            inputText = "Remember me",
                            modifier = Modifier
                        )
                    }

                    CommonTextField(
                        modifier = Modifier,
                        inputText = "Forgot the Password?",
                        isLink = true,
                    )

                }
                CommonButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Login",
                    buttonModifier = Modifier
                        .padding(bottom = dimen_mdpi.x_32_dp),
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}