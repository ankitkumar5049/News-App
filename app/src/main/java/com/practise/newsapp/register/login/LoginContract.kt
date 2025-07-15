package com.practise.newsapp.register.login

import com.practise.newsapp.common.utils.Constants

class LoginContract{
    data class State(
        val username: String = Constants.EMPTY_STRING,
        val password: String = Constants.EMPTY_STRING
    )
}