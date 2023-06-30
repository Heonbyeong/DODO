package com.example.dodo.presentation.todoadd

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dodo.R
import com.example.dodo.presentation.todoadd.components.view.TodoAddItem
import com.example.dodo.presentation.todoadd.components.view.TodoAddSheetLayout
import com.example.dodo.ui.theme.BoldN12
import com.example.dodo.ui.theme.BoldN14
import com.example.dodo.ui.theme.MediumN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray04
import com.example.dodo.ui.theme.gray08
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.KeyboardState
import com.example.dodo.util.dateFormat
import com.example.dodo.util.keyboardAsState
import com.example.dodo.util.noRippleClickable
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoAddScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TodoAddViewModel = hiltViewModel(),
    date: LocalDate = LocalDate.now(),
    isEdit: Boolean = false
) {
    val lazyColumnState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    val focusRequester = LocalFocusManager.current
    val isKeyboardOpen by keyboardAsState()

    val title = remember { mutableStateOf(if (isEdit) "할 일 수정" else "할 일 추가") }
    var todoText by remember { mutableStateOf("") }

    val items = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7) // TODO

    val closeSheet: () -> Unit = {
        coroutineScope.launch { sheetState.hide() }
    }

    val openSheet: () -> Unit = {
        coroutineScope.launch { sheetState.show() }
    }

    BackHandler(sheetState.isVisible) {
        closeSheet()
    }

    LaunchedEffect(Unit) {
        lazyColumnState.scrollToItem(items.size - 1)
    }

    LaunchedEffect(isKeyboardOpen) {
        snapshotFlow { isKeyboardOpen }.collect {
            if (it == KeyboardState.Opened) {
                lazyColumnState.scrollToItem(items.size - 1)
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = { TodoAddSheetLayout(viewModel = viewModel) },
        sheetState = sheetState,
        sheetBackgroundColor = gray09,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(gray08)
                .pointerInput(Unit) {
                    detectTapGestures { focusRequester.clearFocus() }
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gray09)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterStart)
                        .noRippleClickable {
                            navController.popBackStack()
                        },
                    painter = painterResource(id = R.drawable.ic_left_arrow),
                    contentDescription = null,
                    tint = gray0
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = title.value,
                    style = BoldN14,
                    color = gray0
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .weight(1f),
                state = lazyColumnState,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.background(gray09, RoundedCornerShape(30.dp))) {
                        Text(
                            modifier = Modifier.padding(vertical = 6.dp, horizontal = 30.dp),
                            text = date.dateFormat("yyyy년 M월 dd일"),
                            style = BoldN12,
                            color = gray0
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
                itemsIndexed(items = items) { index, item ->
                    TodoAddItem()
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gray09)
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .background(gray08, RoundedCornerShape(30.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .navigationBarsPadding()
                                .padding(horizontal = 15.dp)
                                .weight(1f),
                            value = todoText,
                            onValueChange = { todoText = it },
                            singleLine = true,
                            textStyle = MediumN12,
                            decorationBox = { innerTextField ->
                                if (todoText.isEmpty()) {
                                    androidx.compose.material.Text(
                                        text = "할 일을 입력해주세요",
                                        style = MediumN12,
                                        color = gray04
                                    )
                                }
                                innerTextField()
                            }
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.noRippleClickable {
                                    viewModel.onClickMap()
                                    openSheet()
                                },
                                painter = painterResource(id = R.drawable.ic_bottomsheet_location),
                                contentDescription = null,
                                tint = gray04
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                modifier = Modifier.noRippleClickable {
                                    viewModel.onClickTimeSelector()
                                    openSheet()
                                },
                                painter = painterResource(id = R.drawable.ic_bottomsheet_alarm),
                                contentDescription = null,
                                tint = gray04
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            var tint = if (todoText.isNotEmpty()) gray0 else gray04
                            Icon(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(id = R.drawable.ic_circle_success),
                                contentDescription = null,
                                tint = tint
                            )
                        }
                    }
                }
            }
        }
    }
}