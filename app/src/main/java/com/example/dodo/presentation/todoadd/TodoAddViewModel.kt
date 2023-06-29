package com.example.dodo.presentation.todoadd

import com.example.dodo.presentation.base.BaseViewModel
import com.example.dodo.presentation.base.BottomSheetScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel @Inject constructor() : BaseViewModel<TodoAddState, TodoAddSideEffect>() {
    override val container = container<TodoAddState, TodoAddSideEffect>(TodoAddState())

    fun onClickMap() = intent {
        reduce {
            state.copy(currentSheet = BottomSheetScreen.TodoAddMapBottomSheet)
        }
    }

    fun onClickTimeSelector() = intent {
        reduce {
            state.copy(currentSheet = BottomSheetScreen.TodoAddTimeSelectBottomSheet)
        }
    }
}