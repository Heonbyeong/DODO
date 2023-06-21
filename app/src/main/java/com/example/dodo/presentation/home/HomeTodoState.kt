package com.example.dodo.presentation.home

import com.example.dodo.presentation.base.State
import java.time.LocalDate

data class HomeTodoState(
    val selectedDate: LocalDate = LocalDate.now()
): State