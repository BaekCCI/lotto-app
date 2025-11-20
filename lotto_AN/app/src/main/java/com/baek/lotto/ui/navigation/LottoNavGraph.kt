package com.baek.lotto.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.baek.lotto.ui.screens.machine.LottoMachineRoute
import com.baek.lotto.ui.screens.main.MainRoute
import com.baek.lotto.ui.screens.splash.SplashRoute
import com.baek.lotto.ui.screens.storage.StorageRoute

@Composable
fun LottoNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashRoute(navController)
        }

        composable(Screen.Main.route) {
            MainRoute(navController = navController)
        }
        composable(Screen.LottoMachine.route) {
            LottoMachineRoute(navController = navController)
        }
        composable(Screen.Storage.route) {
            StorageRoute(navController = navController)
        }
    }
}
