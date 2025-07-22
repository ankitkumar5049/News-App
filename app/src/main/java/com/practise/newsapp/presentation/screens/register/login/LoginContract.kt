package com.practise.newsapp.presentation.screens.register.login

import com.practise.newsapp.common.utils.Constants

class LoginContract{
    data class State(
        val username: String = Constants.EMPTY_STRING,
        val password: String = Constants.EMPTY_STRING,
        val usernameError: String = Constants.EMPTY_STRING,
        val passwordError: String = Constants.EMPTY_STRING,
        val rememberMe: Boolean = true,

    )

    sealed class Effect{
        object LaunchSignUpScreen : Effect()
        object LaunchHomeScreen : Effect()
        object LaunchForgotPasswordScreen : Effect()

    }
}