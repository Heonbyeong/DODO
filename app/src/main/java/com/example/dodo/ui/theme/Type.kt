package com.example.dodo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dodo.R

val notosanskr = FontFamily(
    Font(R.font.notosanskr_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.notosanskr_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.notosanskr_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.notosanskr_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.notosanskr_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.notosanskr_thin, FontWeight.Thin, FontStyle.Normal)
)

val cherrybomb = FontFamily(
    Font(R.font.cherrybombone, FontWeight.Normal, FontStyle.Normal)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = cherrybomb,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)
