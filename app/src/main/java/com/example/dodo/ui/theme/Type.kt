package com.example.dodo.ui.theme

import androidx.compose.ui.text.PlatformTextStyle
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

val RegularC32 = TextStyle(
    fontFamily = cherrybomb,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val RegularN12 = TextStyle(
    fontFamily = notosanskr,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val MediumN10 = TextStyle(
    fontFamily = notosanskr,
    fontWeight = FontWeight.Medium,
    fontSize = 10.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val MediumN12 = TextStyle(
    fontFamily = notosanskr,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val MediumN14 = TextStyle(
    fontFamily = notosanskr,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BoldN12 = TextStyle(
    fontFamily = notosanskr,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BoldN14 = TextStyle(
    fontFamily = notosanskr,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BlackN10 = TextStyle(
    fontFamily = notosanskr,
    fontWeight = FontWeight.Black,
    fontSize = 10.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BlackN12 = TextStyle(
    fontFamily = notosanskr,
    fontWeight = FontWeight.Black,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)