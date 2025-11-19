package com.baek.lotto.ui.screens.storage

sealed interface StorageEvent {
    data object OnBackClick : StorageEvent
    data object OnClickEdit : StorageEvent
    data object OnClickEditCancel : StorageEvent
    data object OnClickEditDone : StorageEvent
    data object OnClickReload : StorageEvent
    data object OnClickDraw : StorageEvent

    data class OnToggleGroup(val groupId: Long) : StorageEvent
    data class OnDeleteItem(val groupId: Long, val itemId: Long) : StorageEvent
    data object OnDeleteAll : StorageEvent

    object OnConfirmEdit : StorageEvent
    object OnConfirmDeleteAll : StorageEvent
    object OnDismissDialog : StorageEvent
}
