package com.baek.lotto.ui.screens.machine

sealed interface LottoMachineEvent {

    data object OnBackClick : LottoMachineEvent

    data class OnSelectCount(val count: Int) : LottoMachineEvent

    data object OnClickDraw : LottoMachineEvent

    data object OnClickRetry : LottoMachineEvent

    data object OnClickSave : LottoMachineEvent
}