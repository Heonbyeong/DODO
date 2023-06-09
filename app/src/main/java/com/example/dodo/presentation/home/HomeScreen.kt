package com.example.dodo.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "dodo",
            style = RegularC32
        )
        val todoList = emptyList<Unit>() //TODO 테스트 용
        var selectDate by remember { mutableStateOf("") } // TODO 테스트 용
        HorizontalCalendar(
            onSelectedDate = { date ->
                selectDate = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
            }
        )
        if (todoList.isNotEmpty()) {
            HomeTodoListView()
        } else {
            HomeTodoListEmptyView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }

    }
}