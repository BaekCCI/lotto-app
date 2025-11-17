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

@Composable
fun LottoNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainRoute(navController = navController)
        }
        composable(Screen.LottoMachine.route) {
            LottoMachineRoute(navController = navController)
        }
        composable(Screen.Storage.route) {
            Text("로또 보관함")
        }
    }
}
