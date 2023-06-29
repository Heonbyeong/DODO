package com.example.dodo.presentation.todoadd

import com.example.dodo.presentation.common.BottomSheetScreen
import com.example.dodo.presentation.base.State

data class TodoAddState(
    val currentSheet: BottomSheetScreen = BottomSheetScreen.TodoAddMapBottomSheet
) : State {

}