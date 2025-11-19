package com.baek.lotto.ui.screens.storage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baek.lotto.common.Result
import com.baek.lotto.ui.model.NumberUiModel
import com.baek.lotto.ui.model.StorageGroupUiModel
import com.baek.lotto.ui.model.StorageItemUiModel
import com.baek.lotto.ui.theme.Background
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoGray
import com.baek.lotto.ui.theme.LottoGreen
import com.baek.lotto.ui.theme.LottoRed
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.LottoYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageScreen(
    isEditing: Boolean,
    loadState: Result<Unit>,
    saveState: Result<Unit>,
    dialogState: StorageDialog,
    groupList: List<StorageGroupUiModel>,
    onEvent: (StorageEvent) -> Unit
) {
    val canEdit = (loadState is Result.Success) && groupList.isNotEmpty()
    val pullState = rememberPullToRefreshState()
    val context = LocalContext.current
    LaunchedEffect(saveState) {
        when (saveState) {
            is Result.Success -> {
                Toast
                    .makeText(context, "수정이 완료되었습니다.", Toast.LENGTH_SHORT)
                    .show()
                onEvent(StorageEvent.OnClearSaveState)
            }

            is Result.Error -> {
                Toast
                    .makeText(context, saveState.message ?: "처리에 실패했습니다.", Toast.LENGTH_SHORT)
                    .show()
                onEvent(StorageEvent.OnClearSaveState)
            }

            else -> Unit
        }
    }

    Scaffold(
        containerColor = Background,
        topBar = {
            StorageTopBar(
                isEditing = isEditing,
                canEdit = canEdit,
                onEvent = onEvent
            )
        },
        bottomBar = {
            if (isEditing) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Button(
                        onClick = { onEvent(StorageEvent.OnDeleteAll) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LottoRed
                        ),
                        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 16.dp)
                    ) {
                        Text(text = "전체 삭제하기", style = LottoTypography.labelMedium)
                    }
                }
            }
        }
    ) { innerPadding ->
        PullToRefreshBox(
            state = pullState,
            isRefreshing = if (isEditing) false else (loadState is Result.Loading),
            onRefresh = {
                if (!isEditing) {
                    onEvent(StorageEvent.OnClickReload)
                }
            }
        ) {
            when (loadState) {
                is Result.Success -> {
                    StorageListView(
                        groupList = groupList,
                        onEvent = onEvent,
                        isEditing = isEditing,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }

                is Result.Error -> StorageErrorView(
                    message = loadState.message,
                    onEvent = onEvent
                )

                is Result.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = LottoBlue,
                            strokeWidth = 4.dp
                        )
                    }
                }

                else -> {}
            }
        }
    }
    when (dialogState) {
        StorageDialog.ConfirmDeleteAll -> {
            ConfirmDialog(
                title = "모두 삭제하시겠습니다?",
                message = "삭제한 이후에는 되돌릴 수 없습니다.",
                onConfirm = { onEvent(StorageEvent.OnConfirmDeleteAll) },
                onCancel = { onEvent(StorageEvent.OnDismissDialog) }
            )
        }

        StorageDialog.ConfirmEdit -> {
            ConfirmDialog(
                title = "수정하시겠습니까?",
                message = "수정한 이후에는 되돌릴 수 없습니다.",
                onConfirm = { onEvent(StorageEvent.OnConfirmEdit) },
                onCancel = { onEvent(StorageEvent.OnDismissDialog) }
            )
        }

        else -> Unit
    }
    if (saveState is Result.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    enabled = true,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {},
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = LottoBlue,
                strokeWidth = 4.dp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StorageScreenPreview() {
    val sampleItems = listOf(
        StorageItemUiModel(
            id = 1,
            numbers = listOf(
                NumberUiModel(3, LottoYellow),
                NumberUiModel(11, LottoBlue),
                NumberUiModel(22, LottoRed),
                NumberUiModel(27, LottoGreen),
                NumberUiModel(35, LottoGray),
                NumberUiModel(44, LottoYellow)
            )
        ),
        StorageItemUiModel(
            id = 2,
            numbers = listOf(
                NumberUiModel(7, LottoBlue),
                NumberUiModel(13, LottoRed),
                NumberUiModel(29, LottoYellow),
                NumberUiModel(31, LottoGreen),
                NumberUiModel(38, LottoGray),
                NumberUiModel(45, LottoYellow)
            )
        )
    )

    val sampleGroups = listOf(
        StorageGroupUiModel(
            id = 100,
            date = "2025.11.18",
            isExpanded = true,
            items = sampleItems
        ),
        StorageGroupUiModel(
            id = 101,
            date = "2025.11.17",
            isExpanded = false,
            items = sampleItems
        )
    )

    StorageScreen(
        isEditing = false,
        loadState = Result.Success(Unit),
        saveState = Result.Loading,
        dialogState = StorageDialog.None,
        groupList = sampleGroups,
        onEvent = {}
    )
}
