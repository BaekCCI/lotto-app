package com.baek.lotto.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LottoTypography = Typography(

    displayLarge = TextStyle(
        fontFamily = LottoFont,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = LottoFont,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp
    ),

    /* ---------------------- Headline ---------------------- */

    headlineMedium = TextStyle(
        fontFamily = LottoFont,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = LottoFont,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp
    ),

    /* ---------------------- Title ---------------------- */


    titleLarge = TextStyle(
        fontFamily = LottoFont,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 2.8.sp
    ),
    titleMedium = TextStyle(
        fontFamily = LottoFont,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = LottoFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp
    ),
    /* ---------------------- Body ---------------------- */

    bodyLarge = TextStyle(
        fontFamily = LottoFont,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = LottoFont,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = LottoFont,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.4.sp
    ),

    /* ---------------------- Label ---------------------- */

    labelLarge = TextStyle(
        fontFamily = LottoFont,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.1.sp
    )
)
