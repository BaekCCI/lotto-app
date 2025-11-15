package com.baek.lotto.ui.screens.main

sealed interface MainEvent {
    data object OnClickRandom : MainEvent
    data object OnClickStorage : MainEvent
    data object OnClickDrawTitle : MainEvent
    data class OnSelectDraw(val drawNo: Int) : MainEvent
    data object OnDismissBottomSheet : MainEvent
}