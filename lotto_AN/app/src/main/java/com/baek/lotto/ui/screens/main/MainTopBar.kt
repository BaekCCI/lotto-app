package com.baek.lotto.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baek.lotto.ui.theme.DividerSecondary
import com.baek.lotto.ui.theme.LottoBlue
import com.baek.lotto.ui.theme.LottoGray
import com.baek.lotto.ui.theme.LottoGreen
import com.baek.lotto.ui.theme.LottoRed
import com.baek.lotto.ui.theme.LottoTypography
import com.baek.lotto.ui.theme.LottoYellow
import com.baek.lotto.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Surface)
    ) {
        TopAppBar(
            title = {
                TitleLogo(
                    Modifier
                        .padding(horizontal = 8.dp),
                )
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

@Composable
private fun TitleLogo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "L",
            color = LottoYellow,
            style = LottoTypography.titleLarge
        )
        Text(
            "O",
            color = LottoBlue,
            style = LottoTypography.titleLarge
        )
        Text(
            "T",
            color = LottoRed,
            style = LottoTypography.titleLarge
        )
        Text(
            "T",
            color = LottoGray,
            style = LottoTypography.titleLarge
        )
        Text("O", color = LottoGreen, style = LottoTypography.titleLarge)
    }
}
