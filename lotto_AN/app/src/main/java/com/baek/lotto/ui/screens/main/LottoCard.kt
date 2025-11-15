package com.baek.lotto.ui.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baek.lotto.ui.model.DrawUiModel
import com.baek.lotto.ui.theme.DividerPrimary
import com.baek.lotto.ui.theme.LottoGray
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.Surface
import com.baek.lotto.ui.theme.SurfaceSubtle
import com.baek.lotto.ui.theme.TextPrimary
import com.baek.lotto.ui.theme.TextSecondary

@Composable
fun LottoCard(
    draw: DrawUiModel?,
    onClickDrawTitle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, DividerPrimary),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50))
                    .clickable { onClickDrawTitle() }
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = draw?.title ?: "알 수 없음",
                    color = TextPrimary,
                    style = LottoTypography.bodyLarge
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "more",
                    tint = TextPrimary
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 50.dp),
                thickness = 1.dp,
                color = DividerPrimary
            )
            if (draw != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(SurfaceSubtle)
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            draw.numbers.forEach { (number, color) ->
                                NumberBall(number, color)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("당첨 번호", style = LottoTypography.bodySmall, color = TextSecondary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "plus",
                        tint = LottoGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SurfaceSubtle)
                                .padding(6.dp)
                        ) {
                            NumberBall(
                                draw.bonus.number, draw.bonus.color
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("보너스", style = LottoTypography.bodySmall, color = TextSecondary)
                    }

                }
                Spacer(modifier = Modifier.height(15.dp))

                Row {
                    Text("1등 당첨금", style = LottoTypography.bodyMedium)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(draw.firstPrizeFormatted, style = LottoTypography.bodyLarge)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("원", style = LottoTypography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Text("당첨 복권 수", style = LottoTypography.bodyMedium)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(draw.firstWinnerCount.toString(), style = LottoTypography.bodyLarge)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("개", style = LottoTypography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Composable
private fun NumberBall(number: Int, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                style = LottoTypography.bodyLarge,
                color = Surface
            )
        }
    }
}