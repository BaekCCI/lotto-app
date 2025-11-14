package com.baek.lotto.ui.model

data class DrawUiModel(
    val title: String, //n회차(날짜)
    val numbers: List<NumberUiModel>,
    val bonus: NumberUiModel,
    val firstPrizeFormatted: String, //천단위 구분 쉼표
    val firstWinnerCount: Int
)