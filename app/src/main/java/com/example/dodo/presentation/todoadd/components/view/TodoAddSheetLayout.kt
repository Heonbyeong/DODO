package com.example.dodo.presentation.todoadd.components.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dodo.presentation.common.BottomSheetScreen
import com.example.dodo.presentation.todoadd.TodoAddViewModel
import com.example.dodo.presentation.todoadd.components.bottomsheet.TodoAddMapBottomSheet
import com.example.dodo.presentation.todoadd.components.bottomsheet.TodoAddTimeSelectBottomSheet
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun TodoAddSheetLayout(
    viewModel: TodoAddViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value

    when(state.currentSheet) {
        BottomSheetScreen.TodoAddMapBottomSheet -> TodoAddMapBottomSheet()
        BottomSheetScreen.TodoAddTimeSelectBottomSheet -> TodoAddTimeSelectBottomSheet()
    }
}