package com.baek.lotto.ui.screens.machine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baek.lotto.common.Result
import com.baek.lotto.ui.model.NumberUiModel
import com.baek.lotto.ui.model.RandomLottoUiModel
import com.baek.lotto.ui.theme.Background
import com.baek.lotto.ui.theme.DividerSecondary
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoGray
import com.baek.lotto.ui.theme.LottoGreen
import com.baek.lotto.ui.theme.LottoRed
import com.baek.lotto.ui.theme.LottoTheme
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.LottoYellow
import com.baek.lotto.ui.theme.Surface
import com.baek.lotto.ui.theme.TextPrimary

@Composable
fun LottoMachineScreen(
    selectedCount: Int? = null,
    drawResult: Result<List<RandomLottoUiModel>>,
    onEvent: (LottoMachineEvent) -> Unit
) {
    Scaffold(
        containerColor = Background,
        topBar = { TopBar(onEvent = { onEvent(LottoMachineEvent.OnBackClick) }) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            when (drawResult) {
                is Result.Success -> ResultScreen(
                    result = drawResult.data,
                    onEvent = onEvent
                )

                else -> SelectScreen(
                    selectedCount = selectedCount,
                    onEvent = onEvent
                )
            }
        }
        if (drawResult is Result.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
                    .clickable(
                        enabled = true,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {}
            ) {
                LoadingScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onEvent: (LottoMachineEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Surface)
    ) {
        CenterAlignedTopAppBar(
            title = { Text("랜덤 로또 뽑기", style = LottoTypography.headlineSmall) },
            navigationIcon = {
                IconButton(onClick = { onEvent(LottoMachineEvent.OnBackClick) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "back",
                        tint = TextPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Surface,
                scrolledContainerColor = Surface
            )
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = 1.dp,
            color = DividerSecondary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LottoMachineScreen_Select_Preview() {
    LottoTheme {
        LottoMachineScreen(
            selectedCount = null,
            drawResult = Result.Loading,
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun LottoMachineScreen_Loading_Preview() {
    LottoTheme {
        LottoMachineScreen(
            selectedCount = 3,
            drawResult = Result.Loading,
            onEvent = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LottoMachineScreen_Result_Preview() {
    LottoTheme {
        LottoMachineScreen(
            selectedCount = 3,
            drawResult = Result.Success(
                listOf(
                    RandomLottoUiModel(
                        listOf(
                            NumberUiModel(8, LottoYellow),
                            NumberUiModel(12, LottoRed),
                            NumberUiModel(15, LottoGreen),
                            NumberUiModel(29, LottoBlue),
                            NumberUiModel(40, LottoGray),
                            NumberUiModel(45, LottoRed),
                        )
                    ),
                    RandomLottoUiModel(
                        listOf(
                            NumberUiModel(8, LottoYellow),
                            NumberUiModel(12, LottoRed),
                            NumberUiModel(15, LottoGreen),
                            NumberUiModel(29, LottoBlue),
                            NumberUiModel(40, LottoGray),
                            NumberUiModel(45, LottoRed),
                        )
                    ),
                    RandomLottoUiModel(
                        listOf(
                            NumberUiModel(8, LottoYellow),
                            NumberUiModel(12, LottoRed),
                            NumberUiModel(15, LottoGreen),
                            NumberUiModel(29, LottoBlue),
                            NumberUiModel(40, LottoGray),
                            NumberUiModel(45, LottoRed),
                        )
                    )
                )
            ),
            onEvent = {}
        )
    }
}

