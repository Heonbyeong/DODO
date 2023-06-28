package com.example.dodo.presentation.home

import com.example.dodo.presentation.base.SideEffect
import java.time.LocalDate

sealed class HomeTodoSideEffect : SideEffect {
    data class SelectedDate(val date: LocalDate) : HomeTodoSideEffect()
}
