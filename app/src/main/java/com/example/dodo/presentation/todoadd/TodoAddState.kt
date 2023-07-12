package com.example.dodo.presentation.todoadd

import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.entity.todoadd.SearchAddressEntity
import com.example.dodo.presentation.common.BottomSheetScreen
import com.example.dodo.presentation.base.State
import java.time.LocalDate
import java.time.LocalTime

data class TodoAddState(
    val isLoading: Boolean = false,
    val currentSheet: BottomSheetScreen = BottomSheetScreen.TodoAddMapBottomSheet,
    val todoList: List<TodoEntity> = emptyList(),
    val todo: String = "",
    val date: LocalDate = LocalDate.now(),
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val addressText: String = "",
    val newAddress: String = "",
    val oldAddress: String = "",
    val jusoList: List<SearchAddressEntity.JusoEntity> = emptyList(),
    val time: LocalTime? = LocalTime.now(),
    val hasDestination: Boolean = false,
    val hasTime: Boolean = false,
    val isEdit: Boolean = false,
    val id: Int = 0
) : State {

    val hasOldAddress: Boolean
        get() = oldAddress.isNotEmpty()

    val titleText: String
        get() = if (isEdit) "할 일 수정" else "할 일 추가"
}