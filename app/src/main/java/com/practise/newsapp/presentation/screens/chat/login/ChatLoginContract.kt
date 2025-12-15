package com.practise.newsapp.presentation.screens.chat.login

import com.practise.newsapp.common.utils.Constants

class ChatLoginContract{
    data class State(
        val username: String = Constants.EMPTY_STRING,
        val password: String = Constants.EMPTY_STRING,
        val usernameError: String = Constants.EMPTY_STRING,
        val passwordError: String = Constants.EMPTY_STRING,
        val dob: String = Constants.EMPTY_STRING,
        val gender: String = Constants.EMPTY_STRING,
        val termsAndCondition: Boolean = false
        )

    sealed class Effect{
        object LaunchChatHomeScreen : Effect()
    }
}