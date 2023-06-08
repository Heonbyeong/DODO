package com.example.dodo.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dodo.presentation.home.components.HorizontalCalendar
import com.example.dodo.ui.theme.RegularC32
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "dodo",
            style = RegularC32
        )
        var selectDate by remember { mutableStateOf("") } // TODO 테스트 용
        HorizontalCalendar(
            onSelectedDate = { date ->
                selectDate = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
            }
        )
    }
}