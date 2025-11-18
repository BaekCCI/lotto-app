package com.baek.lotto.ui.screens.storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.TextSecondary

@Composable
fun StorageEmptyView(
    onEvent: (StorageEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "저장된 로또가 없어요\n랜덤 로또를 뽑아보세요!",
            style = LottoTypography.bodyMedium,
            color = TextSecondary,
            textAlign = Center
        )
        Spacer(Modifier.height(10.dp))

        TextButton(onClick = { onEvent(StorageEvent.OnClickDraw) }) {
            Text(
                text = "랜덤 로또 뽑기",
                style = LottoTypography.labelMedium,
                color = LottoBlue,
            )
        }
    }
}