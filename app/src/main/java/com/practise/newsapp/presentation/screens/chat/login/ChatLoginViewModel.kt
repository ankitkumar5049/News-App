package com.practise.newsapp.presentation.screens.chat.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.practise.newsapp.common.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel

class ChatLoginViewModel: BaseViewModel() {
    var state by mutableStateOf(ChatLoginContract.State())
    var effects = Channel<ChatLoginContract.Effect>(Channel.UNLIMITED)

    fun isFilled(): Boolean{
        return state.username.isNotEmpty() &&
                state.gender.isNotEmpty() &&
                state.dob.isNotEmpty() &&
                state.termsAndCondition
    }
}