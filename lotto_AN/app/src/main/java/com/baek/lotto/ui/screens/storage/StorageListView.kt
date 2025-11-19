package com.baek.lotto.ui.screens.storage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.baek.lotto.R
import com.baek.lotto.ui.components.NumberBall
import com.baek.lotto.ui.model.StorageGroupUiModel
import com.baek.lotto.ui.model.StorageItemUiModel
import com.baek.lotto.ui.theme.DividerSecondary
import com.baek.lotto.ui.theme.LottoRed
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.Surface
import com.baek.lotto.ui.theme.SurfaceSubtle
import com.baek.lotto.ui.theme.TextPrimary

@Composable
fun StorageListView(
    groupList: List<StorageGroupUiModel>,
    onEvent: (StorageEvent) -> Unit,
    isEditing: Boolean,
    modifier: Modifier
) {
    if (groupList.isEmpty()) {
        StorageEmptyView(onEvent = onEvent)
    } else {
        LazyColumn(
            modifier = modifier
        ) {
            items(
                items = groupList,
                key = { it.id }
            ) { group ->
                StorageGroup(
                    group = group,
                    isEditing = isEditing,
                    onEvent = onEvent
                )
            }
        }

    }
}

@Composable
fun StorageGroup(
    group: StorageGroupUiModel,
    isEditing: Boolean,
    onEvent: (StorageEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = group.date,
                style = LottoTypography.bodyMedium,
                color = TextPrimary,
            )
            IconButton(onClick = {
                onEvent(StorageEvent.OnToggleGroup(group.id))
            }) {
                Icon(
                    imageVector = if (group.isExpanded)
                        Icons.Default.KeyboardArrowUp
                    else
                        Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = TextPrimary
                )
            }
        }
        AnimatedVisibility(visible = group.isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                group.items.forEach { item ->
                    StorageItemRow(
                        groupId = group.id,
                        item = item,
                        isEditing = isEditing,
                        onEvent = onEvent
                    )
                    Spacer(Modifier.height(8.dp))
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    thickness = 1.dp,
                    color = DividerSecondary
                )
            }
        }
    }
}

@Composable
private fun StorageItemRow(
    groupId: Long,
    item: StorageItemUiModel,
    isEditing: Boolean,
    onEvent: (StorageEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceSubtle)
                .padding(if (isEditing) 6.dp else 10.dp),
            horizontalArrangement = Arrangement.spacedBy(if (isEditing) 8.dp else 10.dp)
        ) {
            item.numbers.forEach { (number, color) ->
                NumberBall(number, color, false)
            }
        }
        if (isEditing) {
            IconButton(
                onClick = {
                    onEvent(StorageEvent.OnDeleteItem(groupId, item.id))
                },
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove_circle),
                    contentDescription = "delete",
                    tint = LottoRed
                )
            }
        }
    }
}