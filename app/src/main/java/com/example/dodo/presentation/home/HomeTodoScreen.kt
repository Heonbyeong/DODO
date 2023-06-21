package com.example.dodo.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dodo.presentation.home.components.bottomsheet.HomeTodoMapBottomSheet
import com.example.dodo.presentation.home.components.calendar.HorizontalCalendar
import com.example.dodo.presentation.home.components.todolist.HomeTodoListAddView
import com.example.dodo.presentation.home.components.todolist.HomeTodoListEmptyView
import com.example.dodo.presentation.home.components.todolist.HomeTodoListItem
import com.example.dodo.ui.theme.RegularC32
import com.example.dodo.ui.theme.gray09
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeTodoViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { sheetState.currentValue }.collect {
            if (it == ModalBottomSheetValue.Hidden) {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = { HomeTodoMapBottomSheet() },
        sheetState = sheetState,
        sheetBackgroundColor = gray09,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = "dodo",
                style = RegularC32
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    HorizontalCalendar()
                }
                val todoList = listOf<Int>(1, 2, 3, 4, 5) // TODO 테스트 용
                if (todoList.isNotEmpty()) {
                    itemsIndexed(items = todoList) { index, data ->
                        HomeTodoListItem(modifier = Modifier.fillMaxWidth())
                    }
                    item {
                        HomeTodoListAddView(
                            modifier = Modifier.padding(top = 20.dp),
                            onClickAdd = { coroutineScope.launch { sheetState.show() } }
                        )
                        Spacer(modifier = Modifier.height(75.dp))
                    }
                } else {
                    item {
                        HomeTodoListEmptyView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 100.dp)
                        )
                    }
                }
            }
        }
    }
}