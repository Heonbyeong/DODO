package com.example.dodo.presentation.todoadd.components.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.example.dodo.presentation.common.BottomSheetButton
import com.example.dodo.presentation.todoadd.TodoAddViewModel
import com.example.dodo.ui.theme.BoldN14
import com.example.dodo.ui.theme.MediumN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray04
import com.example.dodo.util.timeFormat
import org.orbitmvi.orbit.compose.collectAsState
import java.time.LocalTime
import java.util.Locale

@Composable
fun TodoAddTimeSelectBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: TodoAddViewModel = hiltViewModel(),
    closeSheet: () -> Unit
) {
    val state = viewModel.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.onChangeTime(LocalTime.now())
    }



    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "시간 설정",
            style = BoldN14,
            color = gray0
        )
        Spacer(modifier = Modifier.height(20.dp))
        WheelTimePicker(
            modifier = Modifier.padding(bottom = 20.dp),
            size = DpSize(300.dp, 128.dp),
            textColor = gray0,
            textStyle = MediumN12,
            timeFormat = TimeFormat.AM_PM,
            selectorProperties = WheelPickerDefaults.selectorProperties(
                color = gray04.copy(alpha = 0.2f),
                border = null
            ),
            onSnappedTime = viewModel::onChangeTime
        )
        Spacer(modifier = Modifier.height(30.dp))
        BottomSheetButton(
            text = state.time.timeFormat("a h시 mm분", Locale.KOREAN),
            enabled = true,
            onClick = {
                viewModel.onClickSetTime()
                closeSheet()
            }
        )
    }
}