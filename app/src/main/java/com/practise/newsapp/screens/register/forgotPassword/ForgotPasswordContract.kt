package com.practise.newsapp.screens.register.forgotPassword

import com.practise.newsapp.common.utils.Constants

class ForgotPasswordContract {
    data class State(
        val emailOrPhone: String = Constants.EMPTY_STRING,
        val otp: String = Constants.EMPTY_STRING,
    )
}