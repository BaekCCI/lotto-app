package com.baek.lotto.ui.screens.machine

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.baek.lotto.ui.components.OutlineButton
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoGray
import com.baek.lotto.ui.theme.LottoGreen
import com.baek.lotto.ui.theme.LottoInactive
import com.baek.lotto.ui.theme.LottoRed
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.LottoYellow
import com.baek.lotto.ui.theme.TextSecondary

@Composable
fun SelectScreen(
    selectedCount: Int?,
    onEvent: (LottoMachineEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "로또 개수를 선택하세요",
            style = LottoTypography.headlineMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(40.dp))
        LottoCountSelector(
            selectedCount = selectedCount,
            onSelect = { count ->
                onEvent(LottoMachineEvent.OnSelectCount(count))
            }
        )
        Spacer(modifier = Modifier.height(80.dp))
        OutlineButton(
            text = "DRAW!",
            enabled = selectedCount != null,
            onClick = { onEvent(LottoMachineEvent.OnClickDraw) }
        )
    }

}

@Composable
fun LottoCountSelector(
    selectedCount: Int?,
    onSelect: (Int) -> Unit
) {
    val colors = listOf(
        LottoYellow,
        LottoBlue,
        LottoRed,
        LottoGray,
        LottoGreen
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        (1..5).forEach { count ->
            BallRadioButton(
                text = "${count}개",
                selected = if (selectedCount == null) null else selectedCount == count,
                selectedColor = colors[count - 1]
            ) {
                onSelect(count)
            }
        }
    }
}

@Composable
fun BallRadioButton(
    text: String,
    selected: Boolean?,
    selectedColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .background(
                if (selected == null || selected) selectedColor else LottoInactive
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = LottoTypography.labelMedium,
            color = if (selected == null || selected) Color.White else TextSecondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_LottoMachineSelectScreen() {
    SelectScreen(
        selectedCount = null,
        onEvent = {}
    )
}



