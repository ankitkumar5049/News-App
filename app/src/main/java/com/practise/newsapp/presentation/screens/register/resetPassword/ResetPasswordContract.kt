package com.practise.newsapp.presentation.screens.register.resetPassword

import com.practise.newsapp.common.utils.Constants

class ResetPasswordContract {
    data class State(
        var newPassword: String = Constants.EMPTY_STRING,
        var confirmNewPassword: String = Constants.EMPTY_STRING
    )
}