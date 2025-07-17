package com.practise.newsapp.navigation

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Signup : NavigationItem(Screen.SIGNUP.name)
}