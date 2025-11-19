package com.baek.lotto.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.baek.lotto.ui.navigation.Screen

@Composable
fun MainRoute(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    MainScreen(
        selectedDraw = viewModel.selectedDraw,
        drawInfoList = viewModel.drawInfoList,
        isBottomSheetVisible = viewModel.isBottomSheetVisible,
        onEvent = { event ->
            when (event) {
                MainEvent.OnClickRandom -> {
                    navController.navigate(Screen.LottoMachine.route)
                }

                MainEvent.OnClickStorage -> {
                    navController.navigate(Screen.Storage.route)
                }

                else -> {
                    viewModel.onEvent(event)
                }
            }
        }
    )
}