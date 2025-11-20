package com.baek.lotto.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.baek.lotto.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(navController: NavController) {
    SplashScreen()
    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }
}