package com.practise.newsapp.screens.register.resetPassword

import com.practise.newsapp.common.utils.Constants

class ResetPasswordContract {
    data class State(
        var password: String = Constants.EMPTY_STRING,
        var confirmPassword: String = Constants.EMPTY_STRING
    )
}