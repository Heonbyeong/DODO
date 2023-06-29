package com.example.dodo.presentation.todoadd

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import com.example.dodo.R
import com.example.dodo.presentation.todoadd.components.view.TodoAddItem
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun TodoAddScreen(
    modifier: Modifier = Modifier,
    viewModel: TodoAddViewModel = hiltViewModel(),
    date: LocalDate = LocalDate.now(),
    isEdit: Boolean = false
) {
    val lazyColumnState = rememberLazyListState()
    val isKeyboardOpen by keyboardAsState()
    val focusRequester = LocalFocusManager.current

    val title = remember { mutableStateOf(if (isEdit) "할 일 수정" else "할 일 추가") }
    var todoText by remember { mutableStateOf("") }

    val items = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7) // TODO

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
                    .align(Alignment.CenterStart),
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
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(start = 15.dp),
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
                    modifier = Modifier.align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bottomsheet_location),
                        contentDescription = null,
                        tint = gray04
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
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