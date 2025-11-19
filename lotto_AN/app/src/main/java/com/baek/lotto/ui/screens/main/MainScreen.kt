package com.baek.lotto.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.baek.lotto.common.Result
import com.baek.lotto.ui.model.DrawInfoUiModel
import com.baek.lotto.ui.model.DrawUiModel
import com.baek.lotto.ui.model.NumberUiModel
import com.baek.lotto.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    selectedDraw: Result<DrawUiModel>,
    drawInfoList: List<DrawInfoUiModel>,
    isBottomSheetVisible: Boolean = false,
    onEvent: (MainEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Scaffold(
        containerColor = Background,
        topBar = { MainTopBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(50.dp))

            LottoCard(
                drawState = selectedDraw,
                onEvent = onEvent
            )

            Spacer(Modifier.height(50.dp))

            Button(
                onClick = { onEvent(MainEvent.OnClickRandom) },
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .size(width = 200.dp, height = 50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LottoBlue,
                    contentColor = Surface
                ),
            ) {
                Text(
                    text = "랜덤 로또 뽑기",
                    style = LottoTypography.labelLarge
                )
            }
            Button(
                onClick = { onEvent(MainEvent.OnClickStorage) },
                modifier = Modifier
                    .size(width = 200.dp, height = 50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LottoGreen,
                    contentColor = Surface
                ),
            ) {
                Text(
                    text = "로또 보관함",
                    style = LottoTypography.labelLarge
                )
            }
        }
        if (isBottomSheetVisible) {
            DrawSelectBottomSheet(
                drawInfoList = drawInfoList,
                selectedDraw = if (selectedDraw is Result.Success) selectedDraw.data.drawNo else null,
                sheetState = sheetState,
                onSelect = { onEvent(MainEvent.OnSelectDraw(it)) },
                onDismiss = { onEvent(MainEvent.OnDismissBottomSheet) }
            )
        }
    }
}

@Preview(showBackground = true, name = "Main - 기본")
@Composable
fun MainScreenPreview() {
    val sampleDraw = DrawUiModel(
        drawNo = 1186,
        title = "1196회차(2025.11.01)",
        numbers = listOf(
            NumberUiModel(8, LottoYellow),
            NumberUiModel(12, LottoRed),
            NumberUiModel(15, LottoGreen),
            NumberUiModel(29, LottoBlue),
            NumberUiModel(40, LottoGray),
            NumberUiModel(45, LottoRed),
        ),
        bonus = NumberUiModel(14, Color(0xFF60A5FA)),
        firstPrizeFormatted = "2,001,627,550",
        firstWinnerCount = 15
    )

    MaterialTheme {
        MainScreen(
            selectedDraw = Result.Success(sampleDraw),
            drawInfoList = listOf(
                DrawInfoUiModel(1196, "1196회차(2025.11.01)"),
                DrawInfoUiModel(1195, "1195회차(2025.10.25)"),
                DrawInfoUiModel(1194, "1194회차(2025.10.18)"),
                DrawInfoUiModel(1193, "1193회차(2025.10.11)"),
                DrawInfoUiModel(1192, "1192회차(2025.11.01)"),
                DrawInfoUiModel(1191, "1191회차(2025.10.25)"),
                DrawInfoUiModel(1190, "1190회차(2025.10.18)"),
                DrawInfoUiModel(1189, "1189회차(2025.10.11)"),
                DrawInfoUiModel(1188, "1188회차(2025.10.11)"),
                DrawInfoUiModel(1187, "1187회차(2025.10.11)"),
                DrawInfoUiModel(1186, "1186회차(2025.10.11)"),
                DrawInfoUiModel(1185, "1185회차(2025.10.11)"),
                DrawInfoUiModel(1184, "1184회차(2025.10.11)"),
                DrawInfoUiModel(1183, "1183회차(2025.10.11)"),
                DrawInfoUiModel(1182, "1182회차(2025.10.11)"),
                DrawInfoUiModel(1181, "1181회차(2025.10.11)"),
                DrawInfoUiModel(1180, "1180회차(2025.10.11)"),
                ),
            isBottomSheetVisible = false,
            onEvent = {}
        )
    }
}
