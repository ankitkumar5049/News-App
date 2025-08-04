package com.practise.newsapp.presentation.screens.home

import com.practise.newsapp.domain.Articles

class HomeContract {

    data class state(
        var articles: List<Articles> = emptyList(),
    )
}