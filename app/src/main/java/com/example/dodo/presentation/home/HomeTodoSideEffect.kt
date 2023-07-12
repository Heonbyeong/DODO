package com.example.dodo.presentation.home

import com.example.dodo.presentation.base.SideEffect
import java.time.LocalDate

sealed class HomeTodoSideEffect : SideEffect {
    data class MoveToAdd(val date: String, val isEdit: Boolean, val id: Int) : HomeTodoSideEffect()
}
