package com.practise.newsapp.presentation.screens.register.resetPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.practise.newsapp.common.viewmodel.BaseViewModel
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(): BaseViewModel() {
    var state by mutableStateOf(ResetPasswordContract.State())
}