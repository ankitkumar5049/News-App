package com.practise.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practise.newsapp.screens.register.forgotPassword.ForgotPasswordScreen
import com.practise.newsapp.screens.register.login.LoginScreen
import com.practise.newsapp.screens.register.login.LoginViewModel
import com.practise.newsapp.screens.register.signup.SignupScreen
import com.practise.newsapp.screens.register.signup.SignupViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.ForgotPassword.route,
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
            ForgotPasswordScreen()
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
