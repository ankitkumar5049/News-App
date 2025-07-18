package com.practise.newsapp.screens.register.forgotPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.practise.newsapp.common.viewmodel.BaseViewModel
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(): BaseViewModel() {
    var state by mutableStateOf(ForgotPasswordContract.State())
}