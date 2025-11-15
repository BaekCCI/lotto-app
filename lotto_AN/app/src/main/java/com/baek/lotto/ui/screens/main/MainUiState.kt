package com.baek.lotto.ui.screens.main

import com.baek.lotto.ui.model.DrawInfoUiModel
import com.baek.lotto.ui.model.DrawUiModel

data class MainUiState(
    val selectedDraw: DrawUiModel? = null,
    val drawInfoList: List<DrawInfoUiModel> = emptyList(),
    val isBottomSheetVisible: Boolean = false
)