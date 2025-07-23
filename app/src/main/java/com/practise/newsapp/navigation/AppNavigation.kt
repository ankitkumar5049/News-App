package com.practise.newsapp.navigation

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP,
    FORGOT_PASSWORD,
    RESET_PASSWORD,
    SELECT_COUNTRY,
    OTP_VERIFICATION,
    LOGO_SCREEN,
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Signup : NavigationItem(Screen.SIGNUP.name)
    object ForgotPassword : NavigationItem(Screen.FORGOT_PASSWORD.name)
    object ResetPassword : NavigationItem(Screen.RESET_PASSWORD.name)
    object SelectCountry : NavigationItem(Screen.SELECT_COUNTRY.name)
    object OtpVerification : NavigationItem(Screen.OTP_VERIFICATION.name)
    object LogoScreen : NavigationItem(Screen.LOGO_SCREEN.name)
}