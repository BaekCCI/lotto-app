package com.baek.lotto.ui.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object LottoMachine : Screen("lotto_machine")
    data object Storage : Screen("storage")
}