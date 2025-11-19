package com.baek.lotto.ui.screens.storage

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.baek.lotto.ui.navigation.Screen

@Composable
fun StorageRoute(
    navController: NavController,
    viewModel: StorageViewModel = hiltViewModel()
) {
    BackHandler {
        if (viewModel.isEditing) {
            viewModel.onEvent(StorageEvent.OnClickEditCancel)
        } else {
            navController.popBackStack()
        }
    }

    StorageScreen(
        isEditing = viewModel.isEditing,
        loadState = viewModel.loadState,
        saveState = viewModel.saveState,
        dialogState = viewModel.dialogState,
        groupList = viewModel.groupList,
        onEvent = { event ->
            when (event) {
                StorageEvent.OnBackClick -> {
                    navController.popBackStack()
                }

                StorageEvent.OnClickDraw -> {
                    navController.navigate(Screen.LottoMachine.route) {
                        popUpTo(Screen.Main.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }

                else -> {
                    viewModel.onEvent(event)
                }
            }
        }
    )
}