package com.example.dodo.presentation.todoadd

import com.example.dodo.domain.entity.todoadd.SearchAddressEntity
import com.example.dodo.presentation.common.BottomSheetScreen
import com.example.dodo.presentation.base.State
import java.time.LocalTime

data class TodoAddState(
    val isLoading: Boolean = false,
    val currentSheet: BottomSheetScreen = BottomSheetScreen.TodoAddMapBottomSheet,
    val todo: String = "",
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val addressText: String = "",
    val newAddress: String = "",
    val oldAddress: String = "",
    val jusoList: List<SearchAddressEntity.JusoEntity> = emptyList(),
    val time: LocalTime = LocalTime.now(),
    val hasDestination: Boolean = false,
    val hasTime: Boolean = false
) : State {

    val hasOldAddress = oldAddress.isNotEmpty()
}