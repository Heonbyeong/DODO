package com.example.dodo.presentation.home.components.bottomsheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.example.dodo.R
import com.example.dodo.presentation.common.BottomSheetButton
import com.example.dodo.presentation.home.HomeTodoViewModel
import com.example.dodo.presentation.todoadd.components.bottomsheet.TodoAddMapBottomSheet
import com.example.dodo.presentation.todoadd.components.bottomsheet.TodoAddTimeSelectBottomSheet
import com.example.dodo.ui.theme.BoldN14
import com.example.dodo.ui.theme.MediumN12
import com.example.dodo.ui.theme.MediumN14
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray03
import com.example.dodo.ui.theme.gray04
import com.example.dodo.ui.theme.gray07
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.noRippleClickable
import com.example.dodo.util.timeFormat
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeTodoAddBottomSheet(
    parentSheetState: ModalBottomSheetState,
    viewModel: HomeTodoViewModel = hiltViewModel()
) {
    var todoText by remember { mutableStateOf("") }
    var locationText by remember { mutableStateOf("장소 선택 안 함") }
    var timeText by remember { mutableStateOf("시간 설정 안 함") }
    var showTimePicker by remember { mutableStateOf(false) }
    var enabled = todoText.isNotEmpty()

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch {
            parentSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
            sheetState.hide()
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                else -> {}
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = { TodoAddTimeSelectBottomSheet(closeSheet = {}) },
        sheetState = sheetState,
        sheetBackgroundColor = gray09,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(gray09)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "할 일 추가",
                style = BoldN14,
                color = gray0
            )
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                value = todoText,
                onValueChange = { todoText = it },
                singleLine = true,
                textStyle = MediumN14,
                decorationBox = { innerTextField ->
                    if (todoText.isEmpty()) {
                        Text(
                            text = "할 일을 입력해주세요",
                            style = MediumN14,
                            color = gray04
                        )
                    }
                    innerTextField()
                }
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                thickness = 1.dp,
                color = gray07
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bottomsheet_location),
                    contentDescription = null,
                    tint = gray0
                )
                Text(
                    modifier = Modifier.noRippleClickable {
                        coroutineScope.launch {
                            parentSheetState.animateTo(ModalBottomSheetValue.Expanded)
                            sheetState.show()
                        }
                    },
                    text = locationText,
                    style = MediumN12,
                    color = gray03
                )
            }
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = gray07
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bottomsheet_alarm),
                    contentDescription = null,
                    tint = gray0
                )
                Text(
                    modifier = Modifier.noRippleClickable {
                        showTimePicker = !showTimePicker
                        timeText = "시간 설정 안 함"
                    },
                    text = timeText,
                    style = MediumN12,
                    color = gray03
                )
            }
            if (showTimePicker) {
                WheelTimePicker(
                    modifier = Modifier.padding(bottom = 20.dp),
                    size = DpSize(300.dp, 128.dp),
                    textColor = gray0,
                    textStyle = MediumN12,
                    timeFormat = TimeFormat.AM_PM,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        color = gray04.copy(alpha = 0.2f),
                        border = null
                    )
                ) { snappedTime ->
                    timeText = snappedTime.timeFormat("a h:mm", Locale.KOREAN)
                }
            } else {
                Spacer(modifier = Modifier.height(148.dp))
            }
            BottomSheetButton(
                text = "추가하기",
                enabled = enabled,
                onClick = {}
            )
        }
    }
}