package com.codemave.mobilecomputing3.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codemave.mobilecomputing3.MobileComputingAppState
import com.codemave.mobilecomputing3.rememberMobileComputingAppState
import com.codemave.mobilecomputing3.ui.home.Home
import com.codemave.mobilecomputing3.ui.login.Login
import com.codemave.mobilecomputing3.ui.payment.Payment

@Composable
fun MobileComputingApp(
    appState: MobileComputingAppState = rememberMobileComputingAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = "login"                           // app start destination is Login
    ) {
        composable(route = "login") {
            Login(navController = appState.navController)
        }
        composable(route = "home") {
            Home(
                navController = appState.navController
            )                                          // if we want to go back to home screen
        }
        composable(route = "payment") {
            Payment(onBackPress = appState::navigateBack)  // surround with lambda oli muotoa
                                                           // appState.navigateBack()
        }
    }
}
