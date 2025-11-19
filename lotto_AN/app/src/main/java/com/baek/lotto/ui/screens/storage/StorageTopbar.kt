package com.baek.lotto.ui.screens.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baek.lotto.ui.theme.DividerSecondary
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.Surface
import com.baek.lotto.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageTopBar(
    isEditing: Boolean,
    canEdit: Boolean = false,
    onEvent: (StorageEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Surface)
    ) {
        CenterAlignedTopAppBar(
            title = {
                if (!isEditing) {
                    Text("랜덤 로또 뽑기", style = LottoTypography.headlineSmall)
                }
            },
            navigationIcon = {
                if (isEditing) {
                    TextButton(onClick = { onEvent(StorageEvent.OnClickEditCancel) }) {
                        Text(
                            text = "취소",
                            style = LottoTypography.labelMedium,
                            color = TextPrimary
                        )
                    }
                } else {
                    IconButton(onClick = { onEvent(StorageEvent.OnBackClick) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "back",
                            tint = TextPrimary
                        )
                    }
                }
            },
            actions = {
                if (isEditing) {
                    TextButton(onClick = { onEvent(StorageEvent.OnClickEditDone) }) {
                        Text(
                            text = "완료",
                            style = LottoTypography.labelMedium,
                            color = LottoBlue
                        )
                    }
                } else if (canEdit) {
                    TextButton(onClick = { onEvent(StorageEvent.OnClickEdit) }) {
                        Text(
                            text = "수정하기",
                            style = LottoTypography.bodyMedium,
                            color = LottoBlue
                        )
                    }
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