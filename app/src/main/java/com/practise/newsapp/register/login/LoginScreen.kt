package com.practise.newsapp.register.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practise.newsapp.common.dimensions.dimen_mdpi
import com.practise.newsapp.common.uiComponents.CommonTextInputFields

@Composable
fun LoginScreen(){

    var username by remember { mutableStateOf("") }
    Box {
        Scaffold { innerPadding ->
            Column (Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp)
            ){
                CommonTextInputFields(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    labelString = "Username",
                    semanticName = "Username",
                    maxChar = 20,
                    labelAsteriskRequired = true,
                )

                Spacer(modifier = Modifier.height(dimen_mdpi.x_1_25))

                CommonTextInputFields(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    labelString = "Username",
                    semanticName = "Username",
                    maxChar = 20,
                    labelAsteriskRequired = true,
                )
            }
        }
    }
}