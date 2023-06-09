package com.example.dodo.presentation.home.components.calendar

import java.util.Locale

data class HorizontalCalendarConfig(
    val yearRange: IntRange = IntRange(1970, 2100),
    val locale: Locale = Locale.KOREAN
)