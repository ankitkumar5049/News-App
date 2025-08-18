package com.practise.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practise.newsapp.presentation.screens.home.HomeScreen
import com.practise.newsapp.presentation.screens.home.HomeViewModel
import com.practise.newsapp.presentation.screens.notification.NotificationScreen
import com.practise.newsapp.presentation.screens.profile.ProfileScreen
import com.practise.newsapp.presentation.screens.register.congratulation.LogoScreen
import com.practise.newsapp.presentation.screens.register.enterOTP.OtpScreen
import com.practise.newsapp.presentation.screens.register.enterOTP.OtpVerificationViewModel
import com.practise.newsapp.presentation.screens.register.forgotPassword.ForgotPasswordScreen
import com.practise.newsapp.presentation.screens.register.forgotPassword.ForgotPasswordViewModel
import com.practise.newsapp.presentation.screens.register.login.LoginScreen
import com.practise.newsapp.presentation.screens.register.login.LoginViewModel
import com.practise.newsapp.presentation.screens.register.resetPassword.ResetPasswordScreen
import com.practise.newsapp.presentation.screens.register.resetPassword.ResetPasswordViewModel
import com.practise.newsapp.presentation.screens.register.signup.SignupScreen
import com.practise.newsapp.presentation.screens.register.signup.SignupViewModel
import com.practise.newsapp.presentation.screens.selectCountry.SelectCountryScreen
import com.practise.newsapp.presentation.screens.selectCountry.SelectCountryViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.Login.route) {
            val viewModel : LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                navigate = navController::navigateWithOptionalPopUp,
            )
        }

        composable(NavigationItem.Signup.route) {
            val viewModel : SignupViewModel = hiltViewModel()
            SignupScreen(
                viewModel = viewModel,
                navigate = navController::navigateWithOptionalPopUp,
            )
        }

        composable(NavigationItem.ForgotPassword.route) {
            val viewModel : ForgotPasswordViewModel = hiltViewModel()
            ForgotPasswordScreen(
                viewModel = viewModel,
                navigate = navController::navigateWithOptionalPopUp,
            )
        }

        composable(NavigationItem.ResetPassword.route) {
            val viewModel : ResetPasswordViewModel = hiltViewModel()
            ResetPasswordScreen(
                viewModel = viewModel,
                navigate = navController::navigateWithOptionalPopUp,
            )
        }

        composable(NavigationItem.SelectCountry.route) {
            val viewModel : SelectCountryViewModel = hiltViewModel()
            SelectCountryScreen(
                viewModel = viewModel,
            )
        }

        composable(NavigationItem.OtpVerification.route) {
            val viewmodel: OtpVerificationViewModel = hiltViewModel()
            OtpScreen(
                navigate = navController::navigateWithOptionalPopUp,
                viewmodel = viewmodel
            )
        }

        composable(NavigationItem.LogoScreen.route) {
            LogoScreen(
                navigate = navController::navigateWithOptionalPopUp,
            )
        }

        composable(NavigationItem.Home.route) {
            val viewmodel : HomeViewModel = hiltViewModel()
            HomeScreen(
                viewmodel = viewmodel,
                navigate = navController::navigateWithOptionalPopUp,
                )
        }

        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }

        composable(NavigationItem.Notification.route) {
            NotificationScreen()
        }

    }
}


fun NavHostController.navigateWithOptionalPopUp(
    targetRoute: String,
    shouldPopUpTo: Boolean,
    popUpToRoute: String? = null,
    inclusive: Boolean = false
) {
    if (shouldPopUpTo && popUpToRoute != null) {
        navigate(targetRoute) {
            popUpTo(popUpToRoute) {
                this.inclusive = inclusive
            }
        }
    } else {
        navigate(targetRoute)
    }
}


fun NavHostController.popBackStackToRoute(
    route: String? = null,
    inclusive: Boolean = false
) {
    if (route.isNullOrEmpty()) {
        popBackStack()
    } else {
        popBackStack(
            route = route,
            inclusive = inclusive
        )
    }
}


/**
 * navController.navigateWithOptionalPopUp(
 *     targetRoute = "home",
 *     shouldPopUpTo = true,
 *     popUpToRoute = "login",
 *     inclusive = true
 * )
 *
 * navController.popBackStackToRoute("dashboard", inclusive = true)
 * */
