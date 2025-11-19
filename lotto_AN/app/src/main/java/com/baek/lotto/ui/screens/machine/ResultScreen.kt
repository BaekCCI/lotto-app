package com.baek.lotto.ui.screens.machine

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.baek.lotto.common.Result
import com.baek.lotto.ui.components.NumberBall
import com.baek.lotto.ui.components.OutlineButton
import com.baek.lotto.ui.model.RandomLottoUiModel
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.Surface
import com.baek.lotto.ui.theme.SurfaceSubtle

@Composable
fun ResultScreen(
    result: List<RandomLottoUiModel>,
    saveState: Result<Unit>,
    onEvent: (LottoMachineEvent) -> Unit
) {

    val context = LocalContext.current
    LaunchedEffect(saveState) {
        when (saveState) {
            is Result.Success -> {
                Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                onEvent(LottoMachineEvent.OnSaveHandled)
            }

            is Result.Error -> {
                Toast.makeText(context, "저장 실패: ${saveState.message}", Toast.LENGTH_SHORT).show()
                onEvent(LottoMachineEvent.OnSaveHandled)
            }

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("결과")
        result.forEach { randomLottoUiModel ->
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(SurfaceSubtle)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                randomLottoUiModel.numbers.forEach { (number, color) ->
                    NumberBall(number, color)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { onEvent(LottoMachineEvent.OnClickSave) }) {
                Text(
                    text = "저장하기",
                    style = LottoTypography.bodyMedium,
                    color = LottoBlue,
                )
            }
        }
        OutlineButton(
            text = "RETRY",
            enabled = true,
            onClick = { onEvent(LottoMachineEvent.OnClickRetry) }
        )
    }
    if (saveState is Result.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
                .clickable(enabled = false) {},
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = LottoBlue,
                strokeWidth = 4.dp
            )
        }
    }
}