package com.baek.lotto.ui.model

data class StorageGroupUiModel(
    val id: Long,
    val date: String,
    val isExpanded: Boolean = true,
    val items: List<StorageItemUiModel> = emptyList()
)