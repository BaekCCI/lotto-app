package com.baek.lotto.ui.screens.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoRed
import com.baek.lotto.ui.theme.Surface
import com.baek.lotto.ui.theme.TextPrimary

@Composable
fun ConfirmDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000))
    ) {
        AlertDialog(
            onDismissRequest = onCancel,
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("확인", color = LottoBlue)
                }
            },
            dismissButton = {
                TextButton(onClick = onCancel) {
                    Text("취소", color = TextPrimary)
                }
            },
            title = { Text(title, color = TextPrimary) },
            text = { Text(message, color = LottoRed) },
            containerColor = Surface
        )
    }
}