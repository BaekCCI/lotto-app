package com.baek.lotto.ui.screens.machine

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LottoMachineRoute(
    navController: NavController,
    viewModel: LottoMachineViewModel = hiltViewModel()
) {
    LottoMachineScreen(
        selectedCount = viewModel.selectedCount,
        drawResult = viewModel.drawResult,
        saveState = viewModel.saveState,
        onEvent = { event ->
            when (event) {
                LottoMachineEvent.OnBackClick -> {
                    navController.popBackStack()
                }

                else -> {
                    viewModel.onEvent(event)
                }
            }
        }
    )
}
