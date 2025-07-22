package com.practise.newsapp.presentation.screens.register.signup

import com.practise.newsapp.common.utils.Constants
class SignupContract{

    data class State(
        val username: String = Constants.EMPTY_STRING,
        val password: String = Constants.EMPTY_STRING,
        val usernameError: String = Constants.EMPTY_STRING,
        val passwordError: String = Constants.EMPTY_STRING,
        val rememberMe: Boolean = true,
        )
}