package com.baek.lotto.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import com.baek.lotto.ui.model.DrawInfoUiModel
import com.baek.lotto.ui.theme.Black
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.SelectedSurface
import com.baek.lotto.ui.theme.Surface
import com.baek.lotto.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawSelectBottomSheet(
    drawInfoList: List<DrawInfoUiModel>,
    selectedDraw: Int?,
    sheetState: SheetState,
    onSelect: (drawNo: Int) -> Unit,
    onDismiss: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = null,
        scrimColor = Color.Black.copy(alpha = 0.4f),
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .heightIn(max = screenHeight * 0.5f)
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = "회차 선택",
                style = LottoTypography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Surface)
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentPadding = PaddingValues(vertical = 5.dp)
            ) {

                items(drawInfoList) { info ->
                    val isSelected = info.drawNo == selectedDraw

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (isSelected) SelectedSurface
                                else Color.Transparent
                            )
                            .clickable { onSelect(info.drawNo) }
                            .padding(vertical = 18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = info.title,
                            style = LottoTypography.titleSmall,
                            fontWeight = if (isSelected) Bold else Normal,
                            color = if (isSelected) Black else TextPrimary
                        )
                    }
                }
            }
        }
    }
}