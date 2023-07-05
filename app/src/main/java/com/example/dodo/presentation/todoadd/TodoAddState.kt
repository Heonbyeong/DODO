package com.example.dodo.presentation.todoadd

import com.example.dodo.presentation.common.BottomSheetScreen
import com.example.dodo.presentation.base.State

data class TodoAddState(
    val currentSheet: BottomSheetScreen = BottomSheetScreen.TodoAddMapBottomSheet,
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val newAddress: String = "",
    val oldAddress: String = ""
) : State {

    val hasOldAddress = oldAddress.isNotEmpty()
}