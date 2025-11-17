package com.baek.lotto.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baek.lotto.ui.theme.DividerPrimary
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoGray
import com.baek.lotto.ui.theme.LottoGreen
import com.baek.lotto.ui.theme.LottoRed
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.LottoYellow
import com.baek.lotto.ui.theme.Surface
import com.baek.lotto.ui.theme.TextSecondary

@Composable
fun OutlineButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val colors = listOf(
        LottoYellow,
        LottoBlue,
        LottoRed,
        LottoGray,
        LottoGreen
    )

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .size(height = 52.dp, width = 200.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled) LottoBlue else DividerPrimary
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Surface,
            contentColor = if (enabled) LottoBlue else DividerPrimary
        )
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            text.forEachIndexed { index, ch ->
                Text(
                    text = "$ch",
                    style = LottoTypography.labelLarge,
                    color = if (enabled) colors[index % 5] else TextSecondary,
                    modifier = Modifier.padding(horizontal = 1.5.dp)
                )
            }
        }
    }
}