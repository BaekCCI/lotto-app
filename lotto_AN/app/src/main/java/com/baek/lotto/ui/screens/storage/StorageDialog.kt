package com.baek.lotto.ui.screens.storage

sealed class StorageDialog {
    object None : StorageDialog()
    object ConfirmDeleteAll : StorageDialog()
    object ConfirmEdit : StorageDialog()
}
