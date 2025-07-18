package com.practise.newsapp.screens.register.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.practise.newsapp.common.viewmodel.BaseViewModel
import javax.inject.Inject

class SignupViewModel @Inject constructor() : BaseViewModel() {
    var state by mutableStateOf(SignupContract.State())
}