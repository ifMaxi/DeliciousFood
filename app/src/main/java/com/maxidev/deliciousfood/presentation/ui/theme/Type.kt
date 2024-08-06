package com.maxidev.deliciousfood.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.R

val openSans = FontFamily(
    Font(R.font.open_sans_light, weight = FontWeight.Light),
    Font(R.font.open_sans, weight = FontWeight.Normal),
    Font(R.font.open_sans_medium, weight = FontWeight.Medium),
    Font(R.font.open_sans_bold, weight = FontWeight.Bold),
    Font(R.font.open_sans_semibold, weight = FontWeight.SemiBold),
    Font(R.font.open_sans_extrabold, weight = FontWeight.ExtraBold)
)

val lobster = FontFamily(
    Font(R.font.lobster_two, weight = FontWeight.Normal),
    Font(R.font.lobster_two_bold, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)